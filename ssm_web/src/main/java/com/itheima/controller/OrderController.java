package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("order")
@RolesAllowed({"ROLE_ADMIN","ROLE_ORDER"})
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("findAllOrder")
    public String findAllOrder(Model model,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "3") Integer pageSize){
      PageInfo<Order> pageInfo =  orderService.findAllOrder(pageNum,pageSize);
      model.addAttribute("pageInfo",pageInfo);
      return "order/orderList";
    }
}
