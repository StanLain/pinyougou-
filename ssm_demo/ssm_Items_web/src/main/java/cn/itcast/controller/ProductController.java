package cn.itcast.controller;

import cn.itcast.pojo.Product;
import cn.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        List<Product> all = productService.findAll();
        return new ModelAndView("product-list","productList",all);
    }

    @RequestMapping("/save.do")
    public String saveOne(Product product) throws Exception{
        System.out.println(product);
       productService.saveOne(product);
        return "redirect:/product/findAll.do";
    }
}
