package com.pluten.user.service.impl;

import com.pluten.user.dao.UserDao;
import com.pluten.user.service.UserService;
import com.pluten.utils.DateUtils;
import com.pluten.utils.Globel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<Map> findRoleList(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
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
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return userDao.findUser(map);
    }

    public List<Map> findAllUser(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return userDao.findAllUser(map);
    }

    public Map findUserByCode(String userCode) {
        Map map = new HashMap();
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("code",userCode);
        List<Map> maps = userDao.findAllUser(map);
        return maps.get(0);
    }

    public List<Map> findUserByDeptId(Integer deptId) {
        Map map = new HashMap();
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("deptId",deptId);
        return userDao.findAllUser(map);
    }

    public List<Map> findUserByDeptCode(String deptCode) {
        Map map = new HashMap();
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("deptCode",deptCode);
        return userDao.findAllUser(map);
    }

    public boolean checkUser(Map map) {
        Integer is = userDao.checkUser(map);
        if(is!=0) return  true;
        else return false;
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
        userDao.deleteUser(id);
        userDao.deleteUserLinkRole(id);
    }

    public void updateUserState(Map map) {
        userDao.updateUserState(map);
    }

    public void updateUser(Map map) {

    }

    public void impower(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
         userDao.impower(map);
    }

    public List<Map> findWjOfRole(Integer roleId) {
        return userDao.findWjOfRole(roleId);
    }

    public void addUser(Map map) {

    }

    public boolean exitRoleNameOrCode(Map role) {
        Integer is = userDao.exitRoleNameOrCode(role);
        if(is!=0) return  true;
        else return false;
    }
}
