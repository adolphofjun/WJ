package com.pluten.wjdc.service.impl;

import com.pluten.utils.*;
import com.pluten.wjdc.controller.WjdcController;
import com.pluten.wjdc.dao.WjdcDao;
import com.pluten.wjdc.service.WjdcService;
import org.apache.commons.lang.StringUtils;
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
        Integer ruleId = wjdcDao.saveRule(rule);
        String id =  rule.get("id")+"";
        ruleId = Integer.parseInt(id);

        System.out.println("=========="+ruleId);
        for(int i=0; i<random.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",random.get(i).get("id"));
            temp.put("per",random.get(i).get("per"));
            //savePrepareWj(ruleId,rule,temp);
            wjdcDao.saveRuleRandom(temp);
        }
        for(int i=0; i<must.size(); i++){
            Map temp = new HashMap();
            temp.put("belong_rule",ruleId);
            temp.put("qu_bank_id",must.get(i).get("id"));
            temp.put("per",must.get(i).get("per"));
            wjdcDao.saveRuleMust(temp);
        }
    }





    public List<Map> findRule(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        List<Map> rs = new ArrayList<Map>();
        List<Map> rules = wjdcDao.findRule(map);
        for(int i=0; i<rules.size(); i++){
            Map rule = rules.get(i);
            Object obj = rule.get("id");;
            Integer ruleId = Integer.parseInt(obj.toString());
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
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        List<Map> rs = new ArrayList<Map>();
        List<Map> temps = wjdcDao.findQuestion(map);
        for(int i=0; i<temps.size(); i++){
            Map temp = temps.get(i);
            //获取题目Id
            Object obj = temp.get("id");
            Integer questionId = Integer.parseInt(obj.toString());
            getQuestionSelect(temp,questionId);
            rs.add(temp);
        }
        return rs;
    }

    public List<Map> findQuestionByBankId(Integer bankId) {
        Map map = new HashMap();
        map.put("bankId",bankId);
        List<Map> rs = new ArrayList<Map>();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        List<Map> temps = wjdcDao.findQuestion(map);
        for(int i=0; i<temps.size(); i++){
            Map temp = temps.get(i);
            //获取题目Id
            Object obj = temp.get("id");
            Integer questionId = Integer.parseInt(obj.toString());
            getQuestionSelect(temp,questionId);
            rs.add(temp);
        }
        return rs;
    }

    /**
     * 获取题目对应的选项
     * @param temp  题目头
     * @param questionId
     */
    private void getQuestionSelect(Map temp, Integer questionId) {
        String must = temp.get("must")+"";
        if("1".endsWith(must)){
            String qu = temp.get("name")+"";
            qu = "(必选题)"+qu;
            temp.put("name",qu);
        }
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
        List roleIds = (List) map.get("roleIds");
        wjdcDao.newWj(map);
        Object ob = map.get("id");
        Integer questionId = Integer.parseInt(ob.toString());
        saveQuForRole(roleIds,questionId);
        //logger.info("roles==========="+roleIds.toString());

    }
    private void saveQuForRole(List roleIds, int id) {
        for(int i=0; i<roleIds.size(); i++){
            Integer temp = Integer.parseInt(roleIds.get(i)+"");
            Map map = new HashMap();
            map.put("roleId",temp);
            map.put("questionnaireId",id);
            wjdcDao.saveQuestionnaireForRole(map);
        }
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

    public List<Map> findQuestionOfWj(Integer wjId,Integer QuNum) throws Exception {

        //
        List<Map> rs = new ArrayList<Map>();
        List<Map> musts = wjdcDao.findMustBankIdByWjId(wjId);
        List<Map> randoms = wjdcDao.findRandomBankIdByWjId(wjId);
        getQuestUtil(rs,musts,1,QuNum);//获取必选题
        getQuestUtil(rs,randoms,0,QuNum);//获取随机体
        return rs;
    }

    /**
     * 根据题库Id获取对应的题目
     * @param rs
     * @param maps
     * @param type
     * @param QuNum
     * @throws Exception
     */
    private void getQuestUtil(List<Map> rs, List<Map> maps, int type,int QuNum) throws  Exception {
        float maxScoreSum = 0f;//最大分值和
        if(rs.size()!=0){
            Map m  = rs.get(rs.size()-1);
            maxScoreSum = (Float)m.get("maxScore");
        }
        int lackNum = 0;//必选题中缺少数量
        for(int i=0; i<maps.size(); i++){
            Map temp = maps.get(i);
            Integer bankId = (Integer) temp.get("bankId");
            //对应题库下所有题目
            List<Map> quests = findQuestTitleByBankId(bankId,type);
            Float per = (Float) temp.get("per");
            float tp =  (per/100f);
            int quNum = (int)(QuNum * tp);//需求数量
            int bankNum = quests.size();//题库数量
            //如果题库中的题目小于等于需求的题目数
            int startMax = 0;//随机起点最大值
            int start = 0;

            if(bankNum<=quNum) {
                //rs.addAll(quests);
                lackNum = quNum-bankNum;
                //将必选题加入列表
                for(int j=0; j<bankNum; j++){
                    Map tempQu = quests.get(j);
                    Object obj = tempQu.get("id");
                    Integer questionId = Integer.parseInt(obj.toString());
                    Object scoreObj = tempQu.get("maxScore");
                    Float maxScore = Float.parseFloat(scoreObj.toString());
                    maxScoreSum+=maxScore;
                    tempQu.put("maxScore",maxScoreSum);
                    getQuestionSelect(tempQu,questionId);
                    rs.add(quests.get(j));
                }
            }else{
                startMax = bankNum - quNum;
                start = MyUtils.getRandom(startMax);
                //将必选题加入列表
                for(int j=start; j<start+quNum; j++){
                    Map tempQu = quests.get(j);
                    Object obj = tempQu.get("id");
                    Integer questionId = Integer.parseInt(obj.toString());
                    Object scoreObj = tempQu.get("maxScore");
                    Float maxScore = Float.parseFloat(scoreObj.toString());
                    maxScoreSum+=maxScore;
                    tempQu.put("maxScore",maxScoreSum);
                    getQuestionSelect(tempQu,questionId);
                    rs.add(quests.get(j));
                }
            }




        }
       /* if(lackNum!=0 && type==1) throw  new MyException("题库中必选题数量不够");
        else if(lackNum!=0 && type==0)throw  new MyException("题库中随机题数量不够");*/
    }

    public void updateQuestState(Map map) {
        wjdcDao.updateQuestState(map);
    }

    public void updateRuleState(Map rule) {
        wjdcDao.updateRuleState(rule);
    }

    public void deleteRuleById(Integer ruleId) {
        wjdcDao.deleteRuleById(ruleId);
        wjdcDao.deleteMustBankOfRuleById(ruleId);
        wjdcDao.deleteRandomBankOfRuleById(ruleId);
    }

    public void saveAnswerWj(Map map) {
        //[{select=A, quId=2, score=2}, {select=A, quId=3, score=5}]
        List list = (List) map.get("data");
        String userId = (String) map.get("userId");
        String quId = (String) map.get("quId");
        String targetId = (String) map.get("targetId");
        getSumScore(list);
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
     * 保存回答后的问卷
     * @param map
     */
    public void saveEmpWj(Map map) {
        //qu_id,an_time,score,creator,creatorTime,targetId
        List list = (List) map.get("data");
        String userId =  map.get("userId")+"";
        String quId =  map.get("quId")+"";
        String targetId =  map.get("targetId")+"";
        Map temp = new HashMap();
        temp.put("quId",quId);
        temp.put("answerId",userId);
        temp.put("score",getSumScore(list));
        temp.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        temp.put("creator",userId);
        temp.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        temp.put("targetId",targetId);
        wjdcDao.saveEmpWj(temp);
    }

    public void CountWj(Map map) {
        List<Float> arrs = wjdcDao.findScoreById(map);
        Float sum = 0f;
        for(int i=1; i<arrs.size()-1; i++){
            sum+= arrs.get(i);
        }
        Map temp = new HashMap();
        temp.put("quId",map.get("targetId"));
        temp.put("score",sum);
        temp.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        temp.put("creator",map.get("creator"));
        temp.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        temp.put("targetId",map.get("targetId"));
        wjdcDao.saveEmpWjTitle(temp);


    }

    public List<Map> findResultTile(Map map) {
        return null;
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

    public Float getSumScore(List list){
        Float sum = 0f;
        //[{select=A, quId=2, score=2}, {select=A, quId=3, score=5}]
        for(int i=0; i<list.size(); i++){
            Map temp = (Map) list.get(i);
            String score =  temp.get("score")+"";
            String quId =  temp.get("quId")+"";
            String select =  temp.get("select")+"";
            if(StringUtils.isNotEmpty(score)){
                Float s = Float.parseFloat(score);
                sum+= s;
            }else{
                throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
            }
        }
        return  sum;
    }

    /**
     * 查询指定题库下必选题和随机题和所有题
     * @param bankId
     * @param type  0 随机题  1 必选题   2所有题
     * @return
     */
    public List<Map> findQuestTitleByBankId(Integer bankId,Integer type){
        Map map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("bankId",bankId);
        if(type==0){
            map.put("must",0);
        }else if(type==1){
            map.put("must",1);
        }else{
            map.put("must",null);
        }
        return wjdcDao.findQuestion(map);
    }




    public static void main(String[] str){
        Random ra =new Random();
        for (int i=0;i<30;i++)
        {System.out.println(MyUtils.getRandom(1));}
    }
}
