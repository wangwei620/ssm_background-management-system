package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IroleDao {
    /**
     * 查找所有角色
     * @return
     */
    @Select("select * from sys_role")
    List<Role> findAllRole();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into sys_role values(com_sequence.nextval,#{roleName},#{roleDesc})")
    void roleAdd(Role role);
    /**
     * 根据user的id 查询所有所有的角色
     */
    @Select("select * from sys_role where id in(select  roleid from sys_user_role where userid=#{userid})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionsByRoleId"))
    })
   List<Role>  findRolesByUserId(Integer id);

    /**
     * 通过id查找角色
     * @param rid
     * @return
     */
    @Select("select * from sys_role where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions",javaType = List.class,
            many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionsByRoleId"))
    })
    Role findRoleById(Integer rid);

    /**
     * 删除以前的id
     * @param roleId
     */
    @Delete("delete from sys_role_permission where roleid= #{roleId}")
    void removeRoleId(Integer roleId);
    @Insert("insert into sys_role_permission values(#{pid},#{roleId})")
    void saveRolePermission(@Param("roleId") Integer roleId, @Param("pid") Integer pid);
}
