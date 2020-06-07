package com.nancy.shirothymeleafdemo.interceptor;

import com.nancy.shirothymeleafdemo.controller.UserController;
import com.nancy.shirothymeleafdemo.entity.Permission;
import com.nancy.shirothymeleafdemo.entity.User;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author chen
 * @date 2020/5/31 23:32
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Resource
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            if (session.getAttribute(UserController.USER_KEY) == null || session.getAttribute(UserController.MENU_KEY) == null) {
                User u = (User) subject.getPrincipal();
                List<Permission> perms = permissionService.selectMenu(u.getUid());
                session.setAttribute(UserController.USER_KEY, u);
                session.setAttribute(UserController.MENU_KEY, perms);
            }
        }
        return true;
    }
}
