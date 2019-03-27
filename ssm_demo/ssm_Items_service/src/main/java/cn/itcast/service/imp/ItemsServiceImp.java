package cn.itcast.service.imp;

import cn.itcast.mapper.ItemsMapper;
import cn.itcast.pojo.Items;
import cn.itcast.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemsServiceImp implements ItemsService {
    @Autowired
    private ItemsMapper itemsMapper;

    public List<Items> findAll() {
        return itemsMapper.findAll();
    }
}
