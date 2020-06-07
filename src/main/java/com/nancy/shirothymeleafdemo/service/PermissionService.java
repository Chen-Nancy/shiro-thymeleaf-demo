package com.nancy.shirothymeleafdemo.service;

import com.nancy.shirothymeleafdemo.entity.Permission;

import java.util.List;

/**
 * @author chen
 * @date 2020/6/2 23:59
 */
public interface PermissionService {
    /**
     * 查询登录用户菜单
     *
     * @param uid
     * @return
     */
    List<Permission> selectMenu(Integer uid);

    /**
     * 通过用户id查询拥有权限
     *
     * @param uid
     * @return
     */
    List<Permission> selectPermsByUid(Integer uid);

    /**
     * 查询所有权限
     *
     * @return
     */
    List<Permission> selectAllPerms();

    /**
     * 通过角色id查询角色拥有权限
     *
     * @param rid
     * @return
     */
    List<Permission> selectPermsByRid(Integer rid);

    /**
     * 修改角色拥有权限
     *
     * @param rid
     * @param pids
     */
    void updateRolePerm(Integer rid, Integer[] pids);
}
