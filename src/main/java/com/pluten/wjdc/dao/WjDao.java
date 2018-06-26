package com.pluten.wjdc.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WjDao {
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

    public Map findRuleByRuId(Map map);

    List<Map> findQuestionByBankId(Map map);
    Map findSelectA(Integer questionId);
    Map findSelectB(Integer questionId);
    Map findSelectC(Integer questionId);
    Map findSelectD(Integer questionId);
    Map findSelectE(Integer questionId);
    Map findSelectF(Integer questionId);
    Map findSelectG(Integer questionId);




    void saveWjTitle(Map map);
    void saveWjQuestion(Map map);
    Integer saveWjSelectA(Map map);
    Integer saveWjSelectB(Map map);
    Integer saveWjSelectC(Map map);
    Integer saveWjSelectD(Map map);
    Integer saveWjSelectE(Map map);
    Integer saveWjSelectF(Map map);
    Integer saveWjSelectG(Map map);
    Integer saveWjSelectH(Map map);


    void saveWjTarget(Map wjTarget);
    void saveWjAnswer(Map wjAnswer);
    /**
     * 判断是否已经答题过
     * @param map  wjId targetId  answerId
     */
    Integer isExisted(Map map);
    List<Map> findWjTitleList(Map map);
    Map findWjTitle(Integer wjId);
    List<Map> findWjTarget(Integer wjId);

    List<Map> findWjQuestionByWj(Integer wjId);
    Map findWjSelectA(Integer questionId);
    Map findWjSelectB(Integer questionId);
    Map findWjSelectC(Integer questionId);
    Map findWjSelectD(Integer questionId);
    Map findWjSelectE(Integer questionId);
    Map findWjSelectF(Integer questionId);
    Map findWjSelectG(Integer questionId);

    /**
     * 用于判断问卷是否可以删除
     * @param wjId
     * @return
     */
    Integer findIsDelete(Integer wjId);

    /**
     * 提交问卷时跟新问卷状态
     * @param map
     */
    void updateWjTitleIsDelete(Map map);
    void deleteWj(Integer wjId);
    void deleteTargetOfWj(Integer wjId);

    void updateWjState(Map map);

}
