package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("product")
@RolesAllowed({"ROLE_ADMIN","ROLE_PRODUCT"})
public class ProductController {
    @Autowired
    private IProductService productService;
    //查找所有列表
    @RequestMapping("findAllProduct")
    public String findAllProduct(Model model,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "3") Integer pageSize){
        PageBean<Product> pb = productService.findAllProduct(pageNum, pageSize);
        //System.out.println("我执行了");
        System.out.println(pb.getList());
        model.addAttribute("pb",pb);
        return "product/productList";
    }
    //通过添加按钮到添加页面
    @RequestMapping("productAddUI")
    public String productAddUI(){
        return "product/productAdd";
    }
    //订单添加页面
    @RequestMapping("productAdd")
    public String productAdd(Product product){
        productService.saveProduct(product);
        //保存完,重定向到订单页面
        return "redirect:/product/findAllProduct";
    }
    //跳转到修改页面
    @RequestMapping("updateProductUI")
    public String updateProductUI(Integer id,Model modle){
        //查询的到的产品对象
      Product product =   productService.findById(id);
      modle.addAttribute("product",product);
        //重定向到跟新页面
        return "product/productUpdate";
    }
    @RequestMapping("updateProduct")
    public String updateProduct(Product product){
        productService.updateProduct(product);
        //修改完跳转展示页面
        return "redirect:/product/findAllProduct";
    }
    /**
     * 删除
     */
    @RequestMapping("deleteById")
    public String deleteById(Integer id){
        productService.deleteById(id);
        return "redirect:/product/findAllProduct";
    }
}
