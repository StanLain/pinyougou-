package cn.itcast.service;

import cn.itcast.pojo.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有订单信息
     * @return
     */
    List<Orders> findAll() throws Exception;
}
