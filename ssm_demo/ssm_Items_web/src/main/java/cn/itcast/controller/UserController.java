package cn.itcast.controller;

import cn.itcast.pojo.UserInfo;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true)int page,@RequestParam(name="pageSize",required = true)int pageSize )throws Exception{
        List<UserInfo> all = userService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(all);
        return new ModelAndView("user-list","pageInfo",pageInfo);
    }

    @RequestMapping("/save.do")
    public String saveOne(UserInfo userInfo)throws Exception{
        userService.saveOne(userInfo);
        return "redirect:/user/findAll.do?page=1&pageSize=3";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
       UserInfo userInfo= userService.findById(id);
        System.out.println(userInfo);
        System.out.println(userInfo.getRoles().size());
       return new ModelAndView("user-show","user",userInfo);
    }
}
