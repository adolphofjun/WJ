package com.pluten.base.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
    /**
     * 保存题库
     * @param map
     */
    void saveBank(Map map);

    /**
     * 查询题库
     * @param map
     * @return
     */
    List<Map> findBank(Map map);

    /**
     * 修改题库
     * @param map
     */
    void updateBank(Map map);

    /**
     * 判断库名和编码是否存在
     * @param map
     * @return
     */
    boolean exitBankNameOrCode(Map map);

    /**
     * 删除题库
     * @param id
     */
    void deleteBank(Integer id);
    /**
     * 查询题库
     * @param map
     * @return
     */
    List<Map> findBankList(Map map);
}
