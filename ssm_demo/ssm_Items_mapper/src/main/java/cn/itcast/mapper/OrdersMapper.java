package cn.itcast.mapper;

import cn.itcast.pojo.Orders;

import java.util.List;

public interface OrdersMapper {

    /**
     * 查询所有orders 延迟加载product
     * @return
     */
    List<Orders> findAll() throws Exception;

    /**
     * 根据订单id查询 orders,product，traveller，member详细信息
     * @param id
     * @return
     * @throws Exception
     */
    Orders findById(String id) throws Exception;
}
