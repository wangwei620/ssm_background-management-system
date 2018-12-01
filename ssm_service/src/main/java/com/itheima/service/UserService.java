package com.itheima.service;

import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    //查询所有用户的数据
    public List<SysUser>  findAllUser();
    //添加用户
    void addUser(SysUser sysUser);
    //通过id查找用户
    SysUser findUserById(Integer id);
    //添加中间表信息
    void saveUserRole(Integer userId, Integer[] ids);
}