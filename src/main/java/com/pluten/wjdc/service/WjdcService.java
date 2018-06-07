package com.pluten.wjdc.service;

import java.util.List;
import java.util.Map;

public interface WjdcService {
    /**
     * 保存规则
     * @param map
     */
    void saveRule(Map map);

    List<Map> findRule(Map map);

    /**
     * 保存题目
     * @param maps
     * @return
     */
    Integer saveQuestion(List<Map> maps);

    /**
     * 保存选项
     * @param map
     * @return
     */
    Integer saveQuestionSelect(Map map);
    List<Map> findQuestion(Map map);

    void newWj(Map map);
    /**
     * 查询问卷的对象
     * @param quId 问卷Id
     * @return
     */
    List<Map> findWjTarget(Integer quId);

    /**
     * 获取问卷的题目
     * @param wjId
     * @return
     */
    List<Map> findQuestionOfWj(Integer wjId);

    void updateRuleState(Map rule);

    void deleteRuleById(Integer ruleId);
}
