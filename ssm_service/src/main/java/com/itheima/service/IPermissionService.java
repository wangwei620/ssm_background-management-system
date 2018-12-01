package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {
    //查找所有的权限列表
    public List<Permission> findAllPermission();
    //添加权限
    void addPermission(Permission permission);
}
