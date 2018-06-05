package com.pluten.wjdc.service.impl;

import com.pluten.utils.DateUtils;
import com.pluten.wjdc.dao.WjdcDao;
import com.pluten.wjdc.service.WjdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("wjdcService")
public class WjdcServiceImpl implements WjdcService {

    @Autowired
    private WjdcDao wjdcDao;

    public void saveRule(Map rule) {
        if(!rule.containsKey("creator")) rule.put("creator","1");
        rule.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));

        List<String> random = null;
        List<String> must = null;
        random = (List<String>) rule.get("random_bank");
        must = (List<String>) rule.get("must_bank");

        Integer ruleId = wjdcDao.saveRule(rule);
        System.out.println("=========="+ruleId);
        for(int i=0; i<random.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",random.get(i));
            wjdcDao.saveRuleRandom(temp);
        }
        for(int i=0; i<must.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",random.get(i));
            wjdcDao.saveRuleMust(temp);
        }



    }
}
