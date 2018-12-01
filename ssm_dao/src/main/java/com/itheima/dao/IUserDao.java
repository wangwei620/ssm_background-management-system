package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 通过name查询用户
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "roles",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IroleDao.findRolesByUserId"))
    })
    SysUser findUserByName(String username);

    /**
     * 查询所有的用户
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> findAllUser();

    /**
     * 添加用户
     * @param sysUser
     */
    @Insert("insert into sys_user values(com_sequence.nextval," +
            "#{username},#{email},#{password},#{phoneNum},#{status})")
    void addUser(SysUser sysUser);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    @Select("select * from sys_user where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "roles",javaType = List.class,
            many = @Many(select = "com.itheima.dao.IroleDao.findRolesByUserId"))
    })
    SysUser findUserById(Integer id);
    //删除后直接添加
    @Delete("delete from sys_user_role where userId = #{userId}")
    void removeRoleFromUser(Integer userId);

    //添加中间表信息
    @Insert("insert into sys_user_role values(#{userId},#{rid})")
   void  saveUserRole(@Param("userId") Integer userId ,@Param("rid") Integer rid);
}
