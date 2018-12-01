package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IPermissionService;
import com.itheima.service.IroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("role")
@RolesAllowed({"ROLE_ADMIN","ROLE_ORDER"})//这是jsr250注解
public class RoleController {
    @Autowired
    private IroleService roleService;
    @Autowired
    private IPermissionService permissionService;
    //查找所有的角色
    @RequestMapping("findAllRole")
    public String findAllRole(Model model){
      List<Role> roleList =  roleService.findAllRole();
      model.addAttribute("roleList",roleList);
      return "role/roleList";
    }
    //跳转到roleadd页面
    @RequestMapping("addRoleUI")
    public String addRoleUI(){
        return "role/roleAdd";
    }
    //角色的添加
    @RequestMapping("roleAdd")
    public String roleAdd(Role role){
        roleService.roleAdd(role);
        return "redirect:/role/findAllRole";
    }
    //管理权限表
    @RequestMapping("managerRolePermissionUI")
    public String managerRolePermissionUI(Integer id,Model model){
//        1.得到角色对象
       Role role =  roleService.findRoleById(id);
       model.addAttribute("role",role);
//        2.得到角色的权限
        List<Permission> rolePermissions = role.getPermissions();
//        3.循环遍历所有的权限,组装并判断字符串页面的判断
        if (rolePermissions!=null&&rolePermissions.size()>0){
            StringBuilder sb = new StringBuilder();
            for (Permission rolePermission : rolePermissions) {
                sb.append(rolePermission.getPermissionName()+",");
            }
            model.addAttribute("pstr",sb.toString());
        }
//        4.所有的权限集合
        List<Permission> permissions = permissionService.findAllPermission();
        model.addAttribute("permissions",permissions);
        return "role/manageRolePermission";
    }
    //维护中间表,根据选择的重新添加到中间表
    @RequestMapping("saveRolePermission")
    public String saveRolePermission(Integer roleId,Integer[] ids){
        roleService.saveRolePermission(roleId,ids);
        return "redirect:/role/findAllRole";
    }
}
