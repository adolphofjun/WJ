package com.pluten.user.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询角色
     * @param map
     * @return
     */
    List<Map> findRoleList(Map map);
    void deleteRole(Integer id);
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

    boolean exitRoleNameOrCode(Map bank);
}
