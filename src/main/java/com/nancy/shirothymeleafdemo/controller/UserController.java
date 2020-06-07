package com.nancy.shirothymeleafdemo.controller;

import com.nancy.shirothymeleafdemo.entity.Permission;
import com.nancy.shirothymeleafdemo.entity.User;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import com.nancy.shirothymeleafdemo.service.RoleService;
import com.nancy.shirothymeleafdemo.service.UserService;
import com.nancy.shirothymeleafdemo.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chen
 * @date 2020/6/4 23:19
 */
@Controller
@Validated
@RequestMapping("user")
public class UserController {
    public static final String USER_KEY = "user";
    public static final String MENU_KEY = "menus";
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @RequestMapping("login")
    public ModelAndView login(HttpSession session, @NotBlank String username, @NotBlank String password, String checked) throws Exception {
        ModelAndView view = new ModelAndView("redirect:/page/index");
        //用户是否记住密码
        boolean isRemember = checked != null;
        //封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Util.onEncrypt(password), isRemember);
        //创建subject实例
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            subject.login(token);
        }
        User u = (User) subject.getPrincipal();
        List<Permission> perms = permissionService.selectMenu(u.getUid());
        session.setAttribute(USER_KEY, u);
        session.setAttribute(MENU_KEY, perms);
        return view;
    }

    @RequiresPermissions({"/user/select"})
    @RequestMapping("select")
    public ModelAndView select(Integer pageIndex) {
        ModelAndView view = new ModelAndView("user/user_show");
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }
        List<User> users = userService.selectUser(pageIndex, PageController.INDEX_NUM);
        int pageCount = userService.selectAllNum(PageController.INDEX_NUM);
        view.addObject("list", users);
        view.addObject("pageIndex", pageIndex);
        view.addObject("pageCount", pageCount);
        return view;
    }

    @RequiresPermissions({"/user/delete"})
    @RequestMapping("delete")
    public ModelAndView delete(@NotNull Integer uid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/user/select?pageIndex=" + pageIndex);
        userService.deleteByUid(uid);
        return view;
    }

    @RequiresPermissions({"/user/insert"})
    @RequestMapping("insert")
    public ModelAndView insert(@Validated User user, Integer pageIndex) throws Exception {
        ModelAndView view = new ModelAndView("redirect:/user/select?pageIndex=" + pageIndex);
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            user.setPassword(Md5Util.onEncrypt(user.getPassword()));
        } else {
            user.setPassword(Md5Util.onEncrypt("123456"));
        }
        userService.insertUser(user);
        return view;
    }

    @RequiresPermissions({"/user/update"})
    @RequestMapping("update")
    public ModelAndView update(@Validated User user, Integer pageIndex) throws Exception {
        ModelAndView view = new ModelAndView("redirect:/user/select?pageIndex=" + pageIndex);
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            user.setPassword(Md5Util.onEncrypt(user.getPassword()));
        }
        userService.updateUser(user);
        return view;
    }

    @RequiresPermissions({"/user/assign"})
    @RequestMapping("assign")
    public ModelAndView assign(@NotNull Integer uid, Integer[] rid, Integer pageIndex) {
        ModelAndView view = new ModelAndView("redirect:/user/select?pageIndex=" + pageIndex);
        roleService.updateUserRole(uid, rid);
        return view;
    }
}
