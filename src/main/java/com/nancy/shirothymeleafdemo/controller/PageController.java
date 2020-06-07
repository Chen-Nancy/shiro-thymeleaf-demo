package com.nancy.shirothymeleafdemo.controller;

import com.nancy.shirothymeleafdemo.entity.Permission;
import com.nancy.shirothymeleafdemo.entity.Role;
import com.nancy.shirothymeleafdemo.entity.User;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import com.nancy.shirothymeleafdemo.service.RoleService;
import com.nancy.shirothymeleafdemo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chen
 * @date 2020/6/3 21:25
 */
@Controller
@Validated
@RequestMapping("page")
public class PageController {
    public static final int INDEX_NUM = 5;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequiresPermissions({"/user/insert"})
    @RequestMapping("user/insert")
    public ModelAndView insertUser(Integer pageIndex) {
        ModelAndView view = new ModelAndView("user/user_operate");
        view.addObject("pageIndex", pageIndex);
        return view;
    }

    @RequiresPermissions({"/user/update"})
    @RequestMapping("user/update")
    public ModelAndView updateUser(@NotNull Integer uid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("user/user_operate");
        User user = userService.selectByUid(uid);
        view.addObject("pageIndex", pageIndex);
        view.addObject("user", user);
        return view;
    }

    @RequiresPermissions({"/user/assign"})
    @RequestMapping("user/assign")
    public ModelAndView assignRole(@NotNull Integer uid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("user/user_assign");
        User user = userService.selectByUid(uid);
        List<Role> roles = roleService.selectAllRoles();
        List<Role> userRoles = roleService.selectRolesByUid(uid);
        view.addObject("pageIndex", pageIndex);
        view.addObject("user", user);
        view.addObject("roles", roles);
        view.addObject("userRoles", userRoles);
        return view;
    }

    @RequiresPermissions({"/role/insert"})
    @RequestMapping("role/insert")
    public ModelAndView insertRole(Integer pageIndex) {
        ModelAndView view = new ModelAndView("role/role_operate");
        view.addObject("pageIndex", pageIndex);
        return view;
    }

    @RequiresPermissions({"/role/update"})
    @RequestMapping("role/update")
    public ModelAndView updateRole(@NotNull Integer rid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("role/role_operate");
        Role role = roleService.selectByRid(rid);
        view.addObject("pageIndex", pageIndex);
        view.addObject("role", role);
        return view;
    }

    @RequiresPermissions({"/role/assign"})
    @RequestMapping("role/assign")
    public ModelAndView assignPerm(@NotNull Integer rid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("role/role_assign");
        Role role = roleService.selectByRid(rid);
        List<Permission> perms = permissionService.selectAllPerms();
        List<Permission> rolePerms = permissionService.selectPermsByRid(rid);
        view.addObject("pageIndex", pageIndex);
        view.addObject("role", role);
        view.addObject("perms", perms);
        view.addObject("rolePerms", rolePerms);
        return view;
    }
}
