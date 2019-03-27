package cn.itcast.controller;

import cn.itcast.pojo.UserInfo;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        List<UserInfo> all = userService.findAll();
        return new ModelAndView("user-list","userList",all);
    }

    @RequestMapping("/save.do")
    public String saveOne(UserInfo userInfo)throws Exception{
        userService.saveOne(userInfo);
        return "redirect:/user/findAll.do";
    }
}
