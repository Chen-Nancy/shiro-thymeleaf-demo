package com.nancy.shirothymeleafdemo.shiro;

import com.nancy.shirothymeleafdemo.entity.Permission;
import com.nancy.shirothymeleafdemo.entity.User;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import com.nancy.shirothymeleafdemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chen
 * @date 2020/6/2 23:58
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = null;
        //查询出用户拥有的所有权限,将用户的权限保存到Shiro中
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Permission> perms = permissionService.selectPermsByUid(user.getUid());
        info = new SimpleAuthorizationInfo();
        //循环权限对象集合 将权限名称保存到SimpleAuthorizationInfo中
        for (Permission perm : perms) {
            info.addStringPermission(perm.getResource());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = userService.selectByUsername((String) authenticationToken.getPrincipal());
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        //用户名不存在
        return null;
    }
}
