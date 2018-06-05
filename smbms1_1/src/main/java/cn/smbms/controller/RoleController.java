package cn.smbms.controller;

import cn.smbms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ModelAndView list(){
    ModelAndView modelAndView=new ModelAndView("");

        return null;
    }


}
