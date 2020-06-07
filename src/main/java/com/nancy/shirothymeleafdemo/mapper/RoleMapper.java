package com.nancy.shirothymeleafdemo.mapper;

import com.nancy.shirothymeleafdemo.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/4 22:22
 */
public interface RoleMapper {
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
     * 通过用户id删除用户角色
     *
     * @param uid
     */
    void deleteUserRoleByUid(Integer uid);

    /**
     * 添加用户角色
     *
     * @param map
     */
    void insertUserRole(Map<String, Object> map);

    /**
     * 分页查询角色
     *
     * @param map
     * @return
     */
    List<Role> selectRole(Map<String, Object> map);

    /**
     * 查询角色总数
     *
     * @return
     */
    Integer selectAllNum();

    /**
     * 通过角色id删除角色
     *
     * @param rid
     */
    void deleteByRid(Integer rid);

    /**
     * 通过角色id删除用户角色
     *
     * @param rid
     */
    void deleteUserRoleByRid(Integer rid);

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
