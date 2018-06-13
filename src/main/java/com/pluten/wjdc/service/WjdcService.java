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

    /**
     * 查询所有的题目
     * @param map
     * @return
     */
    List<Map> findQuestion(Map map);

    /**
     * bankId
     * @param bankId
     * @return
     */
    List<Map> findQuestionByBankId(Integer bankId);

    void newWj(Map map);
    /**
     * 查询问卷的对象
     * @param quId 问卷Id
     * @return
     */
    List<Map> findWjTarget(Integer quId);

    void updateWjState(Map map);

    /**
     * 获取问卷的题目
     * @param wjId
     * @return
     */
    List<Map> findQuestionOfWj(Integer wjId);

    void updateRuleState(Map rule);

    void deleteRuleById(Integer ruleId);

    /**
     * 保存答卷后提交的问卷
     * @param map
     */
    void saveAnswerWj(Map map);

    List<Map> findWj();

    void deleteWjById(Integer wjId);

    /**
     * 保存员工接受过的问卷
     * @param map
     */
    void saveEmpWj(Map map);

    /**
     * 问卷id:quId  目标员工Id  targetId
     * 保存员工接受过的问卷头信息
     * @param map
     */
    void CountWj(Map map);

    /**
     * 查询某一个人的卷头信息
     * @param map  quId   targetId
     * @return
     */
    List<Map> findResultTile(Map map);
}
