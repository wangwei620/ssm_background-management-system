package com.itheima.service.impl;

import com.itheima.dao.IProductDao;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 查找所有商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Product> findAllProduct(Integer pageNum, Integer pageSize) {
        //处理分页的业务逻辑
        //1.获取totalCount 记录数据
        Integer totalCount = productDao.findTotalCount();
        //2.计算总页数
        Integer totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        //3.初始化pageBean
        PageBean<Product> pb = new PageBean<>();
        //4.添加数据到pb中
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        //5.定义其实索引和结束索引
        Integer startIndex = (pageNum-1)*pageSize;
        Integer endIndex = pageNum*pageSize;
        //4.调用dao查询数据
        List<Product> list  = productDao.findAllProduct(startIndex,endIndex);
        pb.setList(list);
        return pb;
    }

    /**
     * 添加
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    /**
     * 修改商品
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }
}
