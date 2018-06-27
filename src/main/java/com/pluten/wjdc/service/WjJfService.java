package com.pluten.wjdc.service;

import java.util.List;
import java.util.Map;

public interface WjJfService {

    /**
     * 保存每个答题者的记录
     * @param map
     */
    void saveEveryOneRecord(Map map);

    /**
     * 保存被问卷者最后的总分
     * @param map
     */
    void saveSumRecord(Map map);

    /**
     * 查询某个被问卷的所有答题者分数记录
     * @param map wjId  targetId
     * @return
     */
    List<Map> findAnswerRecord(Map map);

    /**
     * 查询某一个人的得分纪录
     * @param map  wjId  targetId
     * @return
     */
    List<Map> findAnswerRecordSum(Map map);
}
