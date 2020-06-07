package com.nancy.shirothymeleafdemo.mapper;

import com.nancy.shirothymeleafdemo.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/3 0:38
 */
public interface PermissionMapper {
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
     * 通过角色id删除角色权限
     *
     * @param rid
     */
    void deleteRolePermByRid(Integer rid);

    /**
     * 添加角色权限
     *
     * @param map
     */
    void insertRolePerm(Map<String, Object> map);
}
