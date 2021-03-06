package cn.itcast.mapper;

import cn.itcast.pojo.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 保存一条信息
     * @param product
     * @throws Exception
     */
    void saveOne(Product product) throws Exception;

    /**
     * 通过产品的id  获取产品信息=
     * @throws Exception
     */
    void findById(String pid) throws Exception;
}
