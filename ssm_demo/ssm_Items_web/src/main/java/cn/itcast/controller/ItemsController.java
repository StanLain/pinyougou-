package cn.itcast.controller;

import cn.itcast.pojo.Items;
import cn.itcast.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("get")
    public ModelAndView get(){
        Items items = new Items();
        items.setName("王五");
        items.setId(1);
     /*   ModelAndView mv = new ModelAndView();
        mv.addObject("item",items);
        mv.setViewName("data");*/
        return new ModelAndView("data","item",items);
    }

    @RequestMapping(value = "get2",method ={RequestMethod.POST}  )
    public ModelAndView get2(Items items){
        System.out.println(items);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item",items);
        mv.setViewName("data");
        return mv;
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Items> all = itemsService.findAll();
        Items items = all.get(0);
        model.addAttribute("item",items);
        return "data";
    }
    @RequestMapping("/hobbies")
    public ModelAndView getHobbies(String[] hobbies){
        for (int i=0;i<hobbies.length;i++){
            System.out.println(hobbies[i]);
        }
        return new ModelAndView("data");
    }


}
