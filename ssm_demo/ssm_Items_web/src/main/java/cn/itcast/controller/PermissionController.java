package cn.itcast.controller;

import cn.itcast.pojo.Permission;
import cn.itcast.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true)int page, @RequestParam(name="pageSize",required = true)int pageSize )throws Exception{
        List<Permission> all = permissionService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(all);
        return new ModelAndView("permission-list","pageInfo",pageInfo);
    }

    @RequestMapping("/save.do")
    public String saveOne(Permission permission) throws Exception{
        permissionService.saveOne(permission);
        return "redirect:/permission/findAll.do?page=1&pageSize=3";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
       Permission permission= permissionService.findById(id);
        System.out.println(permission);
        return new ModelAndView("permission-show","permission",permission);
    }

}
