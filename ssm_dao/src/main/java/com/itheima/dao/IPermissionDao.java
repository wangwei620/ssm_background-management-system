package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    /**
     * 查找所有权限
     * @return
     */
    @Select("select * from sys_permission")
    List<Permission> findAllPermission();

    /**
     * 添加权限
     * @param permission
     */
    @Insert("insert into sys_permission values(com_sequence.nextval,#{permissionName},#{url})")
    void addPermission(Permission permission);
    /**
     * 通过角色的id所有的权限
     */
    @Select("select * from sys_permission where id in " +
            "  (select permissionid from sys_role_permission where roleid=#{roleId})")
    public List<Permission> findPermissionsByRoleId(Integer roleId);
}
