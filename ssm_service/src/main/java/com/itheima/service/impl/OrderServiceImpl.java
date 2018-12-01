package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IOrderDao;
import com.itheima.domain.Order;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public PageInfo<Order> findAllOrder(Integer pageNum, Integer pageSize) {
        //1.开启分页插件
        PageHelper.startPage(pageNum,pageSize);//注意下一句就是对他的分页
        List<Order> allOrder = orderDao.findAllOrder();
        //2.返回的对象初始化pageinfo对象
        PageInfo<Order> pageInfo = new PageInfo<>(allOrder);
        return pageInfo;
    }
}
