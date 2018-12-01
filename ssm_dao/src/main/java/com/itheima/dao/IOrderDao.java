package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderDao {
    /**
     * 延迟查询订单表
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "productId",property = "product",javaType = Product.class,
            one = @One(select = "com.itheima.dao.IProductDao.findById")
            ),
    })
    List<Order> findAllOrder();
}
