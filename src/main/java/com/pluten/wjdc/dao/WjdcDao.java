package com.pluten.wjdc.dao;

import java.util.List;
import java.util.Map;

public interface WjdcDao {

    Integer saveRule(Map map);

    Integer saveRuleMust(Map map);

    Integer saveRuleRandom(Map map);
    void updateRuleState(Map rule);
    void deleteRuleById(Integer ruleId);

    /**
     * 删除规则对应的必选题题库
     * @param ruleId
     */
    void deleteMustBankOfRuleById(Integer ruleId);

    /**
     * 删除规则对应的随机题库
     * @param ruleId
     */
    void deleteRandomBankOfRuleById(Integer ruleId);

    List<Map> findRule(Map map);
    List<Map> findRuleMust(Integer ruleId);
    List<Map> findRuleRandom(Integer ruleId);

    /**
     * 保存题目
     * @param map
     */
    void saveQuestion(Map map);
    Integer saveSelectA(Map map);
    Integer saveSelectB(Map map);
    Integer saveSelectC(Map map);
    Integer saveSelectD(Map map);
    Integer saveSelectE(Map map);
    Integer saveSelectF(Map map);
    Integer saveSelectG(Map map);
    Integer saveSelectH(Map map);

    /**
     * 查询题目
     * @return
     */
    List<Map> findQuestion(Map map);
    Map findSelectA(Integer questionId);
    Map findSelectB(Integer questionId);
    Map findSelectC(Integer questionId);
    Map findSelectD(Integer questionId);
    Map findSelectE(Integer questionId);
    Map findSelectF(Integer questionId);
    Map findSelectG(Integer questionId);

    /**
     *
     * @param map  state  quId
     */
    void updateQuestState(Map map);


    /**
     * 新建问卷
     * @param map
     */
    void newWj(Map map);
    void saveQuestionnaireForRole(Map map);

    /**
     * 查询问卷卷头信息
     * @param map
     * @return
     */
    List<Map> findWjTitle(Map map);

    /**
     * 查询问卷的对象
     * @param quId 问卷Id
     * @return
     */
    List<Map> findWjTarget(Integer quId);

    /**
     * 根据问卷Id获取到该问卷中的必选题题库
     * @param wjId
     * @return
     */
    List<Map> findMustBankIdByWjId(Integer wjId);

    /**
     * 据规则获取规则中随机题库
     * @param ruleId
     * @return
     */
    List<Map> findRandomBankIdByRuleId(Integer ruleId);
    /**
     * 根据规则获取规则中必选题库
     * @param ruleId
     * @return
     */
    List<Map> findMustBankIdByRuleId(Integer ruleId);

    /**
     * 据问卷Id获取到该问卷中的随机题题库
     * @param wjId
     * @return
     */
    List<Map> findRandomBankIdByWjId(Integer wjId);

    /**
     * 检查问卷是否可以删除或者改变状态
     * @param wjId
     * @return
     */
    Integer wjIsCanUpdate(Integer wjId);

    void updateWjState(Map map);

    void deleteWjById(Integer wjId);

    /**
     * 保存员工接受过的问卷
     * @param map
     */
    void saveEmpWj(Map map);
    void saveEmpWjTitle(Map map);

    /**
     *  查询某一个人的问卷结果的所有结果
     * @param map
     * @return
     */
    List<Map> findResultByTargetId(Map map);

    /**
     * 查询某一个人的卷头信息
     * @param map  quId   targetId
     * @return
     */
    List<Map> findResultTile(Map map);

    List<Float> findScoreById(Map map);




}
