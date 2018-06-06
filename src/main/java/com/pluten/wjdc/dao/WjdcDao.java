package com.pluten.wjdc.dao;

import java.util.List;
import java.util.Map;

public interface WjdcDao {

    Integer saveRule(Map map);

    Integer saveRuleMust(Map map);

    Integer saveRuleRandom(Map map);

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
     * 新建问卷
     * @param map
     */
    void newWj(Map map);

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
     * 根据问卷Id获取到该问卷中的必选题题库下的题目
     * @param wjId
     * @return
     */
    List<Map> findMustBankIdByWjId(Integer wjId);



}
