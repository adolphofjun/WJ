package com.pluten.wjdc.service.impl;

import com.pluten.utils.*;
import com.pluten.wjdc.controller.WjdcController;
import com.pluten.wjdc.dao.WjdcDao;
import com.pluten.wjdc.service.WjdcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("wjdcService")
@Transactional
public class WjdcServiceImpl implements WjdcService {
    private static Logger logger = Logger.getLogger("WjdcServiceImpl");

    @Autowired
    private WjdcDao wjdcDao;

    public void saveRule(Map rule) {
        MyUtils.checkArgument(rule,"belong_role");
        if(!rule.containsKey("creator")) rule.put("creator","1");
        rule.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));

        List<String> random = null;
        List<String> must = null;
        random = (List<String>) rule.get("random_bank");
        must = (List<String>) rule.get("must_bank");

        Integer ruleId = wjdcDao.saveRule(rule);
        String id =  rule.get("id")+"";
        ruleId = Integer.parseInt(id);
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
            temp.put("qu_bank_id",must.get(i));
            wjdcDao.saveRuleMust(temp);
        }
    }

    public List<Map> findRule(Map map) {
        List<Map> rs = new ArrayList<Map>();
        List<Map> rules = wjdcDao.findRule(map);
        for(int i=0; i<rules.size(); i++){
            Map rule = rules.get(i);
            Integer ruleId = (Integer) rule.get("id");
            List<Map> mustBank = wjdcDao.findRuleMust(ruleId);
            List<Map> randomBank = wjdcDao.findRuleRandom(ruleId);
            rule.put("mustBank",mustBank);
            rule.put("randomBank",randomBank);
            rs.add(rule);
        }
        return rs;
    }

    public Integer saveQuestion(List<Map> maps) {
        for(int i=0; i<maps.size(); i++){
            Map temp = maps.get(i);
            if(!temp.containsKey("creator")) temp.put("creator","1");
            temp.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
            wjdcDao.saveQuestion(temp);
            Object ob = temp.get("id");
            Integer questionId = Integer.parseInt(ob.toString());
            saveSelectMap(temp, questionId);
        }
        return null;
    }

    public Integer saveQuestionSelect(Map map) {
        return null;
    }

    public List<Map> findQuestion(Map map) {
        List<Map> rs = new ArrayList<Map>();
        List<Map> temps = wjdcDao.findQuestion(map);
        for(int i=0; i<temps.size(); i++){
            Map temp = temps.get(i);
            Integer questionId = (Integer) temp.get("id");
            getQuestionSelect(temp,questionId);
            rs.add(temp);
        }
        return rs;
    }

    private void getQuestionSelect(Map temp, Integer questionId) {
        Map tempA = wjdcDao.findSelectA(questionId);
        Map tempB = wjdcDao.findSelectB(questionId);
        Map tempC = wjdcDao.findSelectC(questionId);
        Map tempD = wjdcDao.findSelectD(questionId);
        Map tempE = wjdcDao.findSelectE(questionId);
        Map tempF = wjdcDao.findSelectF(questionId);
        List<Map> selectArr = new ArrayList<Map>();
        if(tempA!=null)selectArr.add(tempA);
        if(tempB!=null)selectArr.add(tempB);
        if(tempC!=null)selectArr.add(tempC);
        if(tempD!=null)selectArr.add(tempD);
        if(tempE!=null)selectArr.add(tempE);
        if(tempF!=null)selectArr.add(tempF);
        temp.put("opetion","NO");
        temp.put("select",selectArr);
    }


    public void newWj(Map map) {
        MyUtils.checkArgument(map,"rule_id");
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        wjdcDao.newWj(map);
    }

    public List<Map> findWjTarget(Integer quId) {
        return wjdcDao.findWjTarget(quId);
    }

    public void updateWjState(Map map) {
        Object t = map.get("id");
        if(null!=t && !"".equals(t+"")){
            Integer wjId = Integer.parseInt(t+"");
            if(0==wjdcDao.wjIsCanUpdate(wjId)){
                wjdcDao.updateWjState(map);
            }else{
                throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
            }
        }else{
            throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
        }

    }

    public List<Map> findQuestionOfWj(Integer wjId) {
        List<Map> rs = new ArrayList<Map>();
        List<Map> musts = wjdcDao.findMustBankIdByWjId(wjId);
        List<Map> randoms = wjdcDao.findRandomBankIdByWjId(wjId);

        List<Map> randoms_ = getRandom_(randoms);

        for(int i=0; i<musts.size(); i++){
            Map temp = musts.get(i);
            Integer questionId = (Integer) temp.get("id");
            getQuestionSelect(temp,questionId);
            rs.add(temp);
        }
        return rs;
    }

    public void updateRuleState(Map rule) {
        wjdcDao.updateRuleState(rule);
    }

    public void deleteRuleById(Integer ruleId) {
        wjdcDao.deleteRuleById(ruleId);
    }

    public List<Map> findWj() {
        return wjdcDao.findWjTitle(null);
    }

    public void deleteWjById(Integer wjId) {
        if(0==wjdcDao.wjIsCanUpdate(wjId)){
            wjdcDao.deleteWjById(wjId);
        }else{
            throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    /**
     * 从随机题中随机选题目
     * @param randoms
     * @return
     */
    private List<Map> getRandom_(List<Map> randoms) {
        List<Map> maps = new ArrayList<Map>();
        Random ra =new Random();
        ra.nextDouble();//获取随机数


        return  maps;
    }

    public void saveSelectMap(Map temp,Integer questionId){
        Map selectMap = new HashMap();
        Integer count = (Integer) temp.get("RowNum");
        for(int i=65; i<65+count; i++){
            char tp = (char) i;
            String selectKey =  ""+tp;
            String scoreKey = tp+"Score";
            selectMap.put("name",temp.get(selectKey));
            selectMap.put("score",temp.get(scoreKey));
            selectMap.put("belong_qu",questionId);
            selectMap.put("title",selectKey);

            if(i==65)wjdcDao.saveSelectA(selectMap);
            if(i==66)wjdcDao.saveSelectB(selectMap);
            if(i==67)wjdcDao.saveSelectC(selectMap);
            if(i==68)wjdcDao.saveSelectD(selectMap);
            if(i==69)wjdcDao.saveSelectE(selectMap);
            if(i==70)wjdcDao.saveSelectF(selectMap);
        }
    }


    public static void main(String[] str){
        for(int i=65; i<65+5; i++){
            char tp = (char) i;
            System.out.println("==="+tp);

        }
    }
}
