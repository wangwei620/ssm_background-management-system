package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermmissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    //查找所有的权限
    @Override
    public List<Permission> findAllPermission() {
        return permissionDao.findAllPermission();
    }
    //添加权限
    @Override
    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }
}
