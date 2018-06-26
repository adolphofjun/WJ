package com.pluten.base.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RuleService {
    void saveRule(Map rule);

    List<Map> findRule(Map map);

    void updateRuleState(Map rule);

    void deleteRule(Integer ruleId);
}
