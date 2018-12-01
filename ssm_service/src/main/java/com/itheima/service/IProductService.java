package com.itheima.service;

import com.itheima.domain.Product;
import com.itheima.utils.PageBean;

public interface IProductService {
    /**
     * 查找所有的商品
     * @return
     */
    public PageBean<Product> findAllProduct(Integer pageNum, Integer pageSize);

    /**
     * 保存商品
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 修改商品
     * @param product
     */
    void updateProduct(Product product);

    /**
     * 通过id找信息
     * @param id
     * @return
     */
    Product findById(Integer id);

    void deleteById(Integer id);
}
