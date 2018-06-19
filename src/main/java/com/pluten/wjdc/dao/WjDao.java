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
    List<Map> findWjTitleList(Map map);
    Map findWjTitle(Integer wjId);

    List<Map> findWjQuestionByWj(Integer wjId);
    Map findWjSelectA(Integer questionId);
    Map findWjSelectB(Integer questionId);
    Map findWjSelectC(Integer questionId);
    Map findWjSelectD(Integer questionId);
    Map findWjSelectE(Integer questionId);
    Map findWjSelectF(Integer questionId);
    Map findWjSelectG(Integer questionId);

}
