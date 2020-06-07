package com.nancy.shirothymeleafdemo.service.impl;

import com.nancy.shirothymeleafdemo.entity.Permission;
import com.nancy.shirothymeleafdemo.mapper.PermissionMapper;
import com.nancy.shirothymeleafdemo.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/3 0:44
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectMenu(Integer uid) {
        return permissionMapper.selectMenu(uid);
    }

    @Override
    public List<Permission> selectPermsByUid(Integer uid) {
        return permissionMapper.selectPermsByUid(uid);
    }

    @Override
    public List<Permission> selectAllPerms() {
        return permissionMapper.selectAllPerms();
    }

    @Override
    public List<Permission> selectPermsByRid(Integer rid) {
        return permissionMapper.selectPermsByRid(rid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRolePerm(Integer rid, Integer[] pids) {
        permissionMapper.deleteRolePermByRid(rid);
        if (pids == null || pids.length <= 0) {
            return;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("rid", rid);
        map.put("pids", pids);
        permissionMapper.insertRolePerm(map);
    }
}
