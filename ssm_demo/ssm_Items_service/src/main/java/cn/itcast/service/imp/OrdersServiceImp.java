package cn.itcast.service.imp;

import cn.itcast.mapper.OrdersMapper;
import cn.itcast.pojo.Orders;
import cn.itcast.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImp implements OrdersService{
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> findAll() throws Exception{
        return ordersMapper.findAll();
    }
}
