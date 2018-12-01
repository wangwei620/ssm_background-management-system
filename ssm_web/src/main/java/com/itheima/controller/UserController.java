package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IroleService;
import com.itheima.service.UserService;
import com.itheima.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("user")
@RolesAllowed("ROLE_ADMIN")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IroleService roleService;
    //查找所有的用户
    @RequestMapping("findAllUser")
    public String findAllUser(Model model) throws CustomException {
        //测试异常
       try {
           //int i = 1/0;
       }catch (Exception e){
           throw new CustomException("-9999","查询所有报错"+e.getMessage()+"传递参数为：==");
       }
     List<SysUser> userList = userService.findAllUser();
     model.addAttribute("userList",userList);
     return "user/userList";
    }
    @RequestMapping("addUserUI")
    public String addUserUI(){
        return "user/userAdd";
    }
    @RequestMapping("addUser")
    public String save(SysUser sysUser,Model model){
        userService.addUser(sysUser);
        return "redirect:/user/findAllUser";
    }
    //维护按钮的实现,通过查询三个数据
    @RequestMapping("managerUserRoleUI")
    public String managerUserRole(Integer id,Model model){
        //1.先通过用户的id获得用户的信息
      SysUser user =   userService.findUserById(id);
        //2.当前用户对应的觉色集合
        List<Role> userRoles = user.getRoles();
        //将所有的用户遍历装成一个字符串,用于页面判断使用
        if (null!=userRoles && userRoles.size()>0){
            StringBuilder sb = new StringBuilder();
            for (Role userRole: userRoles){
                sb.append(userRole.getRoleName()+",");
            }
            model.addAttribute("roleStr",sb.toString());
        }
        //数据的所有角色
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "user/managerUserRole";
    }
    //维护中间表,维护中间表
    @RequestMapping("managerUserRole")
    public String managerUserRole(Integer userId,Integer[] ids){
        userService.saveUserRole(userId,ids);
        return "redirect:/user/findAllUser";
    }
    //转发到usershow
    @RequestMapping("userShowUI")
    public String userShowUI(Integer id,Model model){

        //根据id用户的所有的数据
        SysUser user = userService.findUserById(id);
        System.out.println(user);
        model.addAttribute("user",user);
        return "user/userShow";
    }
}
