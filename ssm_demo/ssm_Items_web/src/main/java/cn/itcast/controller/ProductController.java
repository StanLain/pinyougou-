package cn.itcast.controller;

import cn.itcast.pojo.Product;
import cn.itcast.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") int page,@RequestParam(name = "pageSize",defaultValue = "3")int  pageSize) throws Exception{
        List<Product> all = productService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(all);
        return new ModelAndView("product-list","pageInfo",pageInfo);
    }

    @RequestMapping("/save.do")
    public String saveOne(Product product) throws Exception{
        System.out.println(product);
       productService.saveOne(product);
        return "redirect:/product/findAll.do";
    }
}
