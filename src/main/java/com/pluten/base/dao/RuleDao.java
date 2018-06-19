package com.pluten.base.dao;

import java.util.List;
import java.util.Map;

public interface RuleDao {
    Integer saveRule(Map rule);

    void saveRuleRandom(Map temp);

    void saveRuleMust(Map temp);

    List<Map> findRule(Map map);

    List<Map> findRuleMust(Integer ruleId);

    List<Map> findRuleRandom(Integer ruleId);

    void updateRuleState(Map rule);
}
