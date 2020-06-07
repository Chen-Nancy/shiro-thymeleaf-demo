package com.nancy.shirothymeleafdemo.mapper;

import com.nancy.shirothymeleafdemo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/3 0:30
 */
public interface UserMapper {
    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 通过用户id查询用户信息
     *
     * @param uid
     * @return
     */
    User selectByUid(Integer uid);

    /**
     * 分页查询用户
     *
     * @param map
     * @return
     */
    List<User> selectUser(Map<String, Object> map);

    /**
     * 查询用户总数
     *
     * @return
     */
    Integer selectAllNum();

    /**
     * 通过用户id删除用户
     *
     * @param uid
     */
    void deleteByUid(Integer uid);

    /**
     * 添加用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(User user);
}
