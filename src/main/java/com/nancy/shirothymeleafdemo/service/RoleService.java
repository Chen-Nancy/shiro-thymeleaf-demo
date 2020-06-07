package com.nancy.shirothymeleafdemo.service;

import com.nancy.shirothymeleafdemo.entity.Role;

import java.util.List;

/**
 * @author chen
 * @date 2020/6/4 22:20
 */
public interface RoleService {
    /**
     * 通过角色id查询角色信息
     *
     * @param rid
     * @return
     */
    Role selectByRid(Integer rid);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> selectAllRoles();

    /**
     * 通过用户id查询用户拥有角色
     *
     * @param uid
     * @return
     */
    List<Role> selectRolesByUid(Integer uid);

    /**
     * 修改用户拥有角色
     *
     * @param uid
     * @param rids
     */
    void updateUserRole(Integer uid, Integer[] rids);

    /**
     * 分页查询角色
     *
     * @param pageIndex
     * @param indexNum
     * @return
     */
    List<Role> selectRole(Integer pageIndex, Integer indexNum);

    /**
     * 查询总页数
     *
     * @param indexNum
     * @return
     */
    Integer selectAllNum(Integer indexNum);

    /**
     * 通过角色id删除角色
     *
     * @param rid
     */
    void deleteByRid(Integer rid);

    /**
     * 添加角色
     *
     * @param role
     */
    void insertRole(Role role);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);
}
