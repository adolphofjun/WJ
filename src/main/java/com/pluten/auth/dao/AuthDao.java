package com.pluten.auth.dao;

import java.util.List;
import java.util.Map;

public interface AuthDao {

    /**
     * 查询用户拥有的角色
     * @param userId
     * @return
     */
    List<Map> findRoleOfUser(Integer userId);

    /**
     * 查询角色下的员工
     * @param roleId
     * @return
     */
    List<Map> findUserOfRole(Integer roleId);
}
