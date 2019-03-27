package cn.itcast.controller;

import cn.itcast.pojo.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") int page,@RequestParam(name = "pageSize",defaultValue = "3") int pageSize)throws Exception{
        List<Orders> all = ordersService.findAll(page,pageSize);
        //pageInfo,就是一个分页Bean
        PageInfo pageInfo = new PageInfo(all);
        return new ModelAndView("orders-list","pageInfo",pageInfo);
    }

}
