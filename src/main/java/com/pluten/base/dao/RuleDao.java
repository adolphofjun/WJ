package com.pluten.base.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RuleDao {
    Integer saveRule(Map rule);

    void saveRuleRandom(Map temp);

    void saveRuleMust(Map temp);

    void saveEmpOfRule(Map map);

    List<Map> findRule(Map map);

    List<Map> findRuleMust(Integer ruleId);

    List<Map> findRuleRandom(Integer ruleId);

    List<Map> findEmpOfRule(Integer ruleId);

    void updateRuleState(Map rule);
}
