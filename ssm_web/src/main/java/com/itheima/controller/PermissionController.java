package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("permission")
@RolesAllowed({"ROLE_ADMIN"})
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;
    //查找权限的列表
    @RequestMapping("findPermissionUI")
    public String findPermissionUI(Model model){
      List<Permission> permissionList =  permissionService.findAllPermission();
      model.addAttribute("permission",permissionList);
        return "permission/permissionList";
    }
    //转发到权限添加页面
    @RequestMapping("addPermissionUI")
    public String addPermissionUI(){
        return "permission/permissionAdd";
    }
    //添加权限表
    @RequestMapping("addPermission")
    public String addPermission(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:/permission/findPermissionUI";
    }

}
