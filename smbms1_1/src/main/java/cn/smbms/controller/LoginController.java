package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

import cn.smbms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*登陆服务*/
/*加上注解(需要userservice服务)*/
@Controller
public class LoginController{



        @Autowired
    private UserService userService;

    /**
     * 用于第一次进入登录注册界面,
     * @return
     */
    @RequestMapping(value ="login.html",method =RequestMethod.GET)
    public String login1(){
        return "login";
    }

/*跳转登录页面*///    通过@RequestParam("userCode")找到表单的name值
    @RequestMapping(value ="dologin.html",method =RequestMethod.POST)
    public  String login(@RequestParam("userCode") String userCode, @RequestParam("userPassword")String userPassword, HttpServletRequest request){
//      根据查询出来的对象进行匹配
       User user=userService.login(userCode,userPassword);
            if (user!=null){
                request.getSession().setAttribute(Constants.USER_SESSION,user);
                /*重定向到main进行判断*/
                return "redirect:main";
            }else {
                request.setAttribute("error","用户名和密码错误");
            }
        return "login";
    }

    /*main方法处理结果*/
    @RequestMapping(value = "main")
    private String main(HttpSession session){
        if (session.getAttribute(Constants.USER_SESSION)==null){
     return "login";
}
        return "frame";}


    @RequestMapping(value ="logout",method =RequestMethod.GET)
    public  String loginout(HttpServletRequest request){
        request.getSession().removeAttribute(Constants.USER_SESSION);
        return "login";

    }
}
