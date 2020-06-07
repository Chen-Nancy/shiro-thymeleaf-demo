package com.nancy.shirothymeleafdemo.service;

import com.nancy.shirothymeleafdemo.entity.User;

import java.util.List;

/**
 * @author chen
 * @date 2020/6/2 23:59
 */
public interface UserService {
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
     * @param pageIndex
     * @param indexNum
     * @return
     */
    List<User> selectUser(Integer pageIndex, Integer indexNum);

    /**
     * 查询总页数
     *
     * @param indexNum
     * @return
     */
    Integer selectAllNum(Integer indexNum);

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
