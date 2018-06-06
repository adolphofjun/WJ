package com.pluten.auth.service.impl;

import com.pluten.auth.dao.AuthDao;
import com.pluten.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    public List<Map> findRoleOfUser(Integer userId) {
        return authDao.findRoleOfUser(userId);
    }

    public List<Map> findUserOfRole(Integer roleId) {
        return authDao.findUserOfRole(roleId);
    }
}
