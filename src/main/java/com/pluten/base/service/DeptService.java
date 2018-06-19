package com.pluten.base.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
    boolean exitDeptNameOrCode(Map dept);

    void saveDept(Map dept);

    void updateDeptState(Map dept);

    void deleteDept(Integer deptId);

    List<Map> findDeptList(Map map);
}
