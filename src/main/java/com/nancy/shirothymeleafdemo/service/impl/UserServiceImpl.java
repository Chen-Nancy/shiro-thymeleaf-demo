package com.nancy.shirothymeleafdemo.service.impl;

import com.nancy.shirothymeleafdemo.entity.User;
import com.nancy.shirothymeleafdemo.mapper.RoleMapper;
import com.nancy.shirothymeleafdemo.mapper.UserMapper;
import com.nancy.shirothymeleafdemo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/3 0:43
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User selectByUid(Integer uid) {
        return userMapper.selectByUid(uid);
    }

    @Override
    public List<User> selectUser(Integer pageIndex, Integer indexNum) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("start", (pageIndex - 1) * indexNum);
        map.put("num", indexNum);
        return userMapper.selectUser(map);
    }

    @Override
    public Integer selectAllNum(Integer indexNum) {
        Integer num = userMapper.selectAllNum();
        return num % indexNum == 0 ? num / indexNum : num / indexNum + 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUid(Integer uid) {
        userMapper.deleteByUid(uid);
        roleMapper.deleteUserRoleByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
