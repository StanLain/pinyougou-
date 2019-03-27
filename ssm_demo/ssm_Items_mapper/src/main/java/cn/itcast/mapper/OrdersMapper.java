package cn.itcast.mapper;

import cn.itcast.pojo.Orders;

import java.util.List;

public interface OrdersMapper {

    /**
     * 查询所有orders 延迟加载product
     * @return
     */
    List<Orders> findAll() throws Exception;
}
