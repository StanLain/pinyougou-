package cn.itcast.controller;

import cn.itcast.pojo.Orders;
import cn.itcast.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        List<Orders> all = ordersService.findAll();
        return new ModelAndView("orders-list","ordersList",all);
    }

}
