package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface IroleService {
    //找所有角色
    public List<Role> findAllRole();
    //添加角色
    void roleAdd(Role role);
    //通过id查找角色
    Role findRoleById(Integer id);
    //维护中间表
    void saveRolePermission(Integer roleId, Integer[] ids);
}
