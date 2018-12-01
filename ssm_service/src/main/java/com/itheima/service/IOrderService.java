package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;

public interface IOrderService {
    /**
     * 延迟查询订单
     * @return
     * @param pageNum
     * @param pageSize
     */
    public PageInfo<Order> findAllOrder(Integer pageNum, Integer pageSize);
}
