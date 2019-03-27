package cn.itcast.service.imp;

import cn.itcast.mapper.ProductMapper;
import cn.itcast.pojo.Product;
import cn.itcast.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return  productMapper.findAll();
    }

    public void saveOne(Product product) throws Exception {
        productMapper.saveOne(product);
    }
}
