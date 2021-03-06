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
    boolean exitRoleNameOrCode(Map bank);
    Map findUser(Map map);
    List<Map> findAllUser(Map map);
    Map findUserByCode(String userCode);
    List<Map> findUserByDeptId(Integer deptId);
    List<Map> findUserByDeptCode(String deptCode);

    /**
     * 登录检查用户名和密码
     * @param map
     * @return
     */
    boolean checkUser(Map map);
    void saveUser(Map user);
    boolean exitUserCode(Map user);

    void deleteUser(Integer id);
    void updateUserState(Map map);
    void updateUser(Map map);




    /**
     * 授权
     * @param map
     * @return
     */
    void impower(Map map);

    List<Map> findWjOfRole(Integer roleId);

}
