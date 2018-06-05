package com.pluten.user.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 查询角色
     * @param map
     * @return
     */
    List<Map> findRoleList(Map map);
    void deleteRole(Integer id);
    Integer exitRoleNameOrCode(Map role);
    /**
     * 添加角色
     * @param map
     * @return
     */
    void saveRole(Map map);

    Map findUser(Map map);

    /**
     * 登录检查用户名和密码
     * @param map
     * @return
     */
    Integer checkUser(Map map);

    void deleteUser(Integer id);

    void updateUser(Map map);

    void addUser(Map map);
}
