package com.pluten.base.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeptDao {
    void saveDept(Map map);

    Integer exitDeptNameOrCode(Map map);

    Integer isCanChangeDeptState(Integer deptId_);

    void updateDeptState(Map map);

    void deleteDept(Integer id);

    List<Map> findDeptList(Map map);
}
