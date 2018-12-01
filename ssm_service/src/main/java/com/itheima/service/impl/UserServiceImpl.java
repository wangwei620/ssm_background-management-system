package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;
    //注入密码加密的工具类
    @Autowired
    private  BCryptPasswordEncoder pwdEncoding;

    /**
     * 自定义业务实现类实现框架的目的方法
     * 通过用户名得到User对象用于框架的验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username获取数据库的真实用户
      SysUser sysUser =  userDao.findUserByName(username);
      //创建初始化的测试权限集合
        ArrayList<GrantedAuthority> authorithorities = new ArrayList<>();
        List<Role> userRoles = sysUser.getRoles();
        //真实测试通过当前用户得到对应的角色集合测试
        //循环用户的角色,添加所有的角色
        if (userRoles!=null&&userRoles.size()>0){
            for (Role userRole : userRoles) {
                System.out.println("角色的包括:"+userRole.getRoleName());
                authorithorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
            }
        }
       // authorithorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorithorities);
        return user;
    }
    //查找所有的用户
    @Override
    public List<SysUser> findAllUser() {
        return userDao.findAllUser();
    }
    //添加用户
    @Override
    public void addUser(SysUser sysUser) {
        //把密码给加密了
        String encode = pwdEncoding.encode(sysUser.getPassword());
        //设置密码
        sysUser.setPassword(encode);
        userDao.addUser(sysUser);
    }
    //通过id 查找用户
    @Override
    public SysUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    //添加中间表信息
    @Override
    public void saveUserRole(Integer userId, Integer[] ids) {
        //接受id和维护id数组的关系
        //.先通过userid移除原始的角色
        userDao.removeRoleFromUser(userId);
        //用户没有角色,直接添加
        if (ids!=null&&ids.length>0) {
            for (Integer rid : ids) {
                userDao.saveUserRole(userId,rid);
            }
        }
    }
}
