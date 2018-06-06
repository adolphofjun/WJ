package com.pluten.user.service.impl;

import com.pluten.user.dao.UserDao;
import com.pluten.user.service.UserService;
import com.pluten.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<Map> findRoleList(Map map) {
        return userDao.findRoleList(map);
    }

    public void deleteRole(Integer id) {

    }
    public void saveRole(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
         userDao.saveRole(map);
    }

    public Map findUser(Map map) {
        return null;
    }

    public Integer checkUser(Map map) {
        return null;
    }

    public void saveUser(Map user) {
        userDao.saveUser(user);
    }

    public boolean exitUserCode(Map user) {
        Integer is = userDao.exitUserCode(user);
        if(is!=0) return  true;
        else return false;
    }

    public void deleteUser(Integer id) {

    }

    public void updateUser(Map map) {

    }

    public void impower(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
         userDao.impower(map);
    }

    public void addUser(Map map) {

    }

    public boolean exitRoleNameOrCode(Map role) {
        Integer is = userDao.exitRoleNameOrCode(role);
        if(is!=0) return  true;
        else return false;
    }
}
