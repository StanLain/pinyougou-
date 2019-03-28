package cn.itcast.controller;

import cn.itcast.pojo.Role;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true)int page,@RequestParam(name = "pageSize",required = true)int pageSize) throws Exception{
        List<Role> all = roleService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(all);
        return new ModelAndView("role-list","pageInfo",pageInfo);
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id)throws Exception{
      Role role=  roleService.findById(id);
      return new ModelAndView("role-show","role",role);
    }

    @RequestMapping("/save.do")
    public String saveOne(Role role)throws Exception{
        roleService.saveOne(role);
        return "redirect:/role/findAll.do?page=1&pageSize=3";
    }
}
