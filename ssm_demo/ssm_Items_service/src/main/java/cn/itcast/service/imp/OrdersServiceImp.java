package cn.itcast.service.imp;

import cn.itcast.mapper.OrdersMapper;
import cn.itcast.pojo.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImp implements OrdersService{
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> findAll(int page,int pageSize) throws Exception{
        //分页工具(当前页面页数，每页显示个数)必须写在查询方法之前，中间不能有东西
        PageHelper.startPage(page,pageSize);
        return ordersMapper.findAll();
    }

    public Orders findById(String id) throws Exception {
        return ordersMapper.findById(id);
    }
}
