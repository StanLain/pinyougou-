package cn.itcast.service;

import cn.itcast.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加一条商品信息
     * @param product
     * @throws Exception
     */
    void saveOne(Product product) throws Exception;
}
