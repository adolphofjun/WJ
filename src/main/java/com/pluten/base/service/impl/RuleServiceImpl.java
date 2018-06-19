package com.pluten.base.service.impl;

import com.pluten.base.dao.RuleDao;
import com.pluten.base.service.RuleService;
import com.pluten.utils.DateUtils;
import com.pluten.utils.Globel;
import com.pluten.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    public void saveRule(Map rule) {
        //name=规则2, code=gz2, belong_role=1, visibility=1, bankInfo={must=[{id=1, per=20}, {id=2, per=30}], random=[{id=1, per=20}, {id=2, per=30}]}, num=10}
        if(rule==null) rule = new HashMap();
        rule.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        MyUtils.checkArgument(rule,"belong_role");
        if(!rule.containsKey("creator")) rule.put("creator","1");
        rule.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        Map map = (Map) rule.get("bankInfo");
        List<Map> random = null;
        List<Map> must = null;
        random = (List<Map>) map.get("random");
        must = (List<Map>) map.get("must");
        Integer ruleId = ruleDao.saveRule(rule);
        String id =  rule.get("id")+"";
        ruleId = Integer.parseInt(id);

        System.out.println("=========="+ruleId);
        for(int i=0; i<random.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",random.get(i).get("id"));
            temp.put("per",random.get(i).get("per"));
            //savePrepareWj(ruleId,rule,temp);
            ruleDao.saveRuleRandom(temp);
        }
        for(int i=0; i<must.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",must.get(i).get("id"));
            temp.put("per",must.get(i).get("per"));
            ruleDao.saveRuleMust(temp);
        }
    }

    public List<Map> findRule(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        List<Map> rs = new ArrayList<Map>();
        List<Map> rules = ruleDao.findRule(map);
        for(int i=0; i<rules.size(); i++){
            Map rule = rules.get(i);
            Object obj = rule.get("id");;
            Integer ruleId = Integer.parseInt(obj.toString());
            List<Map> mustBank = ruleDao.findRuleMust(ruleId);
            List<Map> randomBank = ruleDao.findRuleRandom(ruleId);
            rule.put("mustBank",mustBank);
            rule.put("randomBank",randomBank);
            rs.add(rule);
        }
        return rs;
    }

    public void updateRuleState(Map rule) {
        ruleDao.updateRuleState(rule);
    }
}
