package com.itheima.service.impl;

import com.itheima.dao.IroleDao;
import com.itheima.domain.Role;
import com.itheima.service.IroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements IroleService {
    @Autowired
    private IroleDao roleDao;
    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void roleAdd(Role role) {
        roleDao.roleAdd(role);
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.findRoleById(id);
    }
    //维护中间表
    @Override
    public void saveRolePermission(Integer roleId, Integer[] ids) {
        //首先删除原有的表
        roleDao.removeRoleId(roleId);
        //循环添加到中间表
        if (ids!=null&&ids.length>0){
            for (Integer id : ids) {
                roleDao.saveRolePermission(roleId,id);
            }
        }
    }
}
