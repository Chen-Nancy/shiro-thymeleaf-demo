package com.nancy.shirothymeleafdemo.controller;

import com.nancy.shirothymeleafdemo.entity.Role;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import com.nancy.shirothymeleafdemo.service.RoleService;
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
 * @date 2020/6/5 0:25
 */
@Controller
@Validated
@RequestMapping("role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @RequiresPermissions({"/role/select"})
    @RequestMapping("select")
    public ModelAndView select(Integer pageIndex) {
        ModelAndView view = new ModelAndView("role/role_show");
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }
        List<Role> roles = roleService.selectRole(pageIndex, PageController.INDEX_NUM);
        int pageCount = roleService.selectAllNum(PageController.INDEX_NUM);
        view.addObject("list", roles);
        view.addObject("pageIndex", pageIndex);
        view.addObject("pageCount", pageCount);
        return view;
    }

    @RequiresPermissions({"/role/delete"})
    @RequestMapping("delete")
    public ModelAndView delete(@NotNull Integer rid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/role/select?pageIndex=" + pageIndex);
        roleService.deleteByRid(rid);
        return view;
    }

    @RequiresPermissions({"/role/insert"})
    @RequestMapping("insert")
    public ModelAndView insert(@Validated Role role, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/role/select?pageIndex=" + pageIndex);
        roleService.insertRole(role);
        return view;
    }

    @RequiresPermissions({"/role/update"})
    @RequestMapping("update")
    public ModelAndView update(@Validated Role role, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/role/select?pageIndex=" + pageIndex);
        roleService.updateRole(role);
        return view;
    }

    @RequiresPermissions({"/role/assign"})
    @RequestMapping("assign")
    public ModelAndView assign(@NotNull Integer rid, Integer[] pid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/role/select?pageIndex=" + pageIndex);
        permissionService.updateRolePerm(rid, pid);
        return view;
    }
}
