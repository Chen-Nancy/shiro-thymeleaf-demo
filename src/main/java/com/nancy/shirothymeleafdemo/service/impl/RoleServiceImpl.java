package com.nancy.shirothymeleafdemo.service.impl;

import com.nancy.shirothymeleafdemo.entity.Role;
import com.nancy.shirothymeleafdemo.mapper.PermissionMapper;
import com.nancy.shirothymeleafdemo.mapper.RoleMapper;
import com.nancy.shirothymeleafdemo.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/4 22:21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public Role selectByRid(Integer rid) {
        return roleMapper.selectByRid(rid);
    }

    @Override
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRoles();
    }

    @Override
    public List<Role> selectRolesByUid(Integer uid) {
        return roleMapper.selectRolesByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(Integer uid, Integer[] rids) {
        roleMapper.deleteUserRoleByUid(uid);
        if (rids == null || rids.length <= 0) {
            return;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("uid", uid);
        map.put("rids", rids);
        roleMapper.insertUserRole(map);
    }

    @Override
    public List<Role> selectRole(Integer pageIndex, Integer indexNum) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("start", (pageIndex - 1) * indexNum);
        map.put("num", indexNum);
        return roleMapper.selectRole(map);
    }

    @Override
    public Integer selectAllNum(Integer indexNum) {
        Integer num = roleMapper.selectAllNum();
        return num % indexNum == 0 ? num / indexNum : num / indexNum + 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRid(Integer rid) {
        roleMapper.deleteByRid(rid);
        roleMapper.deleteUserRoleByRid(rid);
        permissionMapper.deleteRolePermByRid(rid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }
}
