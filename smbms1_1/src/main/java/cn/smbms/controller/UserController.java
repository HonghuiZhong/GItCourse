package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.utils.Constants;
import cn.smbms.utils.PageSupport;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
/*所有的请求都以/user开头*/
@RequestMapping(value = "user")
public class UserController {


    /**
     * 获得服务,
     */
//    ioc注解
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    /*@RequestParam(name = "userCold",required = false)标注参数属性,可以为空,name指向具体的值*/
    public ModelAndView list(@RequestParam(name = "queryname", required = false) String queryname,
                             @RequestParam(name = "queryUserRole", required = false, defaultValue = "0") String queryUserRole,
                             @RequestParam(name = "pageIndex", required = false, defaultValue = "1") String pageIndex,
                             HttpServletRequest request
    ) {
        //判断是否为空,即选择或没有选择供应商名称
        Integer userRole = null;
        //如果不等于0 获取它的值
        if (!queryUserRole.equals("0")) {
            /*强制转换*/
            userRole = Integer.parseInt(queryUserRole);
        }
        int count = userService.count(queryname, userRole);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(5);
        pageSupport.setTotalCount(count);
        pageSupport.setCurrentPageNo(Integer.parseInt(pageIndex));
        List<User> list = userService.list(queryname, userRole, (pageSupport.getCurrentPageNo() - 1) * pageSupport.getPageSize(), pageSupport.getPageSize());
        /*角色的集合*/
        List<Role> Rolelist = roleService.list();
        request.setAttribute("roleList", Rolelist);
        request.setAttribute("queryUserName", queryname);
        request.setAttribute("totalCount", pageSupport.getTotalCount());
        request.setAttribute("totalPageCount", pageSupport.getTotalPageCount());
        request.setAttribute("currentPageNo", pageSupport.getCurrentPageNo());
        request.setAttribute("userList", list);
        request.setAttribute("queryUserRole", queryUserRole);
        ModelAndView modelAndView = new ModelAndView("userlist");
        return modelAndView;

    }

    /*局部异常*//*

@ExceptionHandler(value = {RuntimeException.class})
    public  String handlerException(RuntimeException e,HttpServletRequest request){
    System.out.println("进入异常处理");
    request.setAttribute("e",e);
    return "error";
}
*/

    /*rost方法获取request传值*/
    @RequestMapping(value = "/jsp/{id}", method = RequestMethod.GET)
    /*获取取出js里面的值*/
    public String Show(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return "userview";
    }


    @RequestMapping(value = "useradd", method = RequestMethod.GET)
    public String useradd(@ModelAttribute("user") User user) {
        return "useradd";
    }

    @RequestMapping(value = "getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoleList() {

        List<Role> roleList = roleService.list();

        return roleList;
    }

    /**
     * 添加用户
     *
     * @param user
     * @param bindingResult
     * @param session
     * @param multipartFile
     * @param request
     * @return
     */

    @RequestMapping(value = "save", method = RequestMethod.POST)
    /*@Valid注解验证*/
    public String save(@Valid User user, BindingResult bindingResult, HttpSession session, @RequestParam(value = "pic", required = false) MultipartFile multipartFile
            , HttpServletRequest request) {
        /*出现异常跳回*/
        if (bindingResult.hasErrors()) {
            return "useradd";
        }
//            判断文件是否为空
        if (!multipartFile.isEmpty()) {
            String path = request.getSession().getServletContext()
                    .getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = multipartFile.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 500000;/*字节,1024字节1kb*/
            if (multipartFile.getSize() > filesize) {//上传大小不得超过 500k
                request.setAttribute("error","文件过大");
                return "useradd";
            } else if (prefix.equalsIgnoreCase("jpg") ||
                    prefix.equalsIgnoreCase("png") ||
                    prefix.equalsIgnoreCase("jpeg") ||
                    prefix.equalsIgnoreCase("pneg")) {
                try {
                    String fileName = System.currentTimeMillis() + "_Personal.jpg";
                    File file = new File(path, fileName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "上传失败，系统异常！");
                    return "useradd";
                }
            } else {
                request.setAttribute("error", "上传失败，文件格式错误！");
                return "useradd";
            }
        }
        User userSession = (User) session.getAttribute(Constants.USER_SESSION);
        user.setCreationDate(new Date());
        //        获取创建着的Id
        user.setCreatedBy(userSession.getId());
        userService.save(user);
        /*返回主页面*/
        return "redirect:/user/list";
    }

    /*直接通过用户名查找是否存在,Map集合*/
    @RequestMapping(value = "ucexist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> ucexist(String userCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userService.getUserByUserCode(userCode);
        if (user != null) {
            result.put("userCode", "exist");
        } else {
            result.put("userCode", "notExist");
        }
        return result;

    }



    /*rost方法获取request传值*/

    /*获取取出js里面的值*/
//    根据id查询user对象
    @RequestMapping(value = "/tomodify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return "usermodify";
    }


    @RequestMapping(value = "usermodify", method = RequestMethod.GET)
    public String usermodify(@ModelAttribute("user") User user) {
        return "usermodify";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modify(@Valid User user, BindingResult bindingResult, HttpSession session) {
        /*出现异常跳回来*/
//        if (bindingResult.hasErrors()){
//            return "usermodify";
//        }
        /*获取当前对象的Id*/
        User userSession = (User) session.getAttribute(Constants.USER_SESSION);//获取当前操作的对象
        user.setModifyDate(new Date());
        user.setModifyBy(userSession.getId());
        userService.modify(user);
        /*返回主页面*/
        return "redirect:/user/list";
    }


    @RequestMapping(value = "/userexist/", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delResult(Integer uid) {

        Map<String, Object> result = new HashMap<String, Object>();
        User user = userService.getUserById(uid);
        if (user.getUserName().equals("admin")) {
            result.put("delResult", "false");
        } else if (user != null) {
            result.put("delResult", "true");
            userService.deleteById(user.getId());
        } else {
            result.put("delResult", "notexist");
        }
        return result;
    }


}
