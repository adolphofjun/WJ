package com.pluten.base.service;

import java.util.List;
import java.util.Map;

public interface RuleService {
    void saveRule(Map rule);

    List<Map> findRule(Map map);

    void updateRuleState(Map rule);
}
