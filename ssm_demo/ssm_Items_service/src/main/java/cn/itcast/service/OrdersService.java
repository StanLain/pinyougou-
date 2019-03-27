package cn.itcast.service;

import cn.itcast.pojo.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有订单信息
     * @return
     */
    List<Orders> findAll(int page,int pageSize) throws Exception;

    /**
     * 根据orders的id查询orders,product，traveller，member详细信息
     * @param id
     * @return
     * @throws Exception
     */
    Orders findById(String id) throws Exception;
}
