package com.pluten.wjdc.dao;

import java.util.Map;

public interface WjdcDao {

    Integer saveRule(Map map);

    Integer saveRuleMust(Map map);

    Integer saveRuleRandom(Map map);
}
