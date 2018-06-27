package com.pluten.user.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
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
    List<Map> findAllUser(Map map);

    /**
     * 登录检查用户名和密码
     * @param map
     * @return
     */
    Integer checkUser(Map map);
    void saveUser(Map user);
    Integer exitUserCode(Map user);
    void deleteUser(Integer userId);
    void deleteUserLinkRole(Integer userId);
    void updateUser(Map map);
    void updateUserState(Map map);

    /**
     * 授权
     * @param map
     * @return
     */
    void impower(Map map);
    List<Map> findWjOfRole(Integer roleId);


}
