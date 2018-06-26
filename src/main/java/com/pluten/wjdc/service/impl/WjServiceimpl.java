package com.pluten.wjdc.service.impl;

import com.pluten.base.dao.RuleDao;
import com.pluten.utils.*;
import com.pluten.wjdc.dao.WjDao;
import com.pluten.wjdc.service.WjService;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("wjService")
public class WjServiceimpl implements WjService {


    private static Logger logger = Logger.getLogger("WjdcServiceImpl");

    @Autowired
    private WjDao wjDao;
    @Autowired
    private RuleDao ruleDao;

    /**
     * 根据规则新建问卷
     * @param map  规则信息
     */
    public void newWj(Map map) {
        String ruleId = map.get("ruleId")+"";
        float sumScore = 0;
        if(ruleId!=null && !"".equals(ruleId)){
            int truleId = Integer.parseInt(ruleId);
            Map tmap = new HashMap();
            tmap.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
            tmap.put("ruleId",truleId);
            Map rule = wjDao.findRuleByRuId(tmap);
            String num = rule.get("num")+"";
            int tnum = Integer.parseInt(num);
            //获取规则对应的题目并保存
            List<Map> rs = new ArrayList<Map>();
            sumScore = getWjQuestion(truleId,tnum,rs);
            map.put("sumScore",sumScore);
            map.put("num",tnum);
            map.put("isDelete",0);
            wjDao.saveWjTitle(map);
            Object  obj = map.get("id");
            Integer wjId = Integer.parseInt(obj.toString());
            saveWjQuestion(wjId,rs);
            List<Map> targets = ruleDao.findEmpOfRule(truleId);
            saveWjTarget(wjId,targets);

        }
    }

    /**
     * 保存问卷对象
     * @param wjId
     * @param masp
     */
    private void saveWjTarget(Integer wjId, List<Map> masp) {
        Map wjTarget = new HashMap();
        wjTarget.put("wjId",wjId);
        for(int i=0; i<masp.size(); i++){
            wjTarget.put("empId",masp.get(i).get("empId"));
            wjTarget.put("empName",masp.get(i).get("empName"));
            wjDao.saveWjTarget(wjTarget);
        }
    }

    public List<Map> findWjTitleList() {
        Map map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return wjDao.findWjTitleList(map);
    }

    public List<Map> findWjTitleUsableList() {
        Map map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("visibility",1);
        return wjDao.findWjTitleList(map);
    }

    public Map findQuestByWjId(Integer wjId) {
        Map wjTitle = wjDao.findWjTitle(wjId);
        List<Map> maps = wjDao.findWjQuestionByWj(wjId);
        for(int i=0; i<maps.size(); i++){
            Map tmap = maps.get(i);
            String quId = tmap.get("id")+"";
            getWjQuestionSelect(tmap,Integer.parseInt(quId));
        }
        wjTitle.put("quest",maps);
        return wjTitle;
    }

    public Integer isExisted(Map map) {
       return wjDao.isExisted(map);
    }

    public List<Map> findWjTarget(Integer wjId) {
        return wjDao.findWjTarget(wjId);
    }

    public void deleteWj(Integer wjId) {
        Integer isDelete = wjDao.findIsDelete(wjId);
        if(isDelete==0){
            wjDao.deleteWj(wjId);
            wjDao.deleteTargetOfWj(wjId);
        }else{
            throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    public void updateWjState(Map map) {
        String id = map.get("wjId")+"";
        int wjId = Integer.parseInt(id);
        Integer isDelete = wjDao.findIsDelete(wjId);
        if(isDelete==0){
           wjDao.updateWjState(map);
        }else{
            throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    private void saveWjQuestion(Integer wjId, List<Map> rs) {
        for(int i=0; i<rs.size(); i++){
            Map tmap = rs.get(i);
            tmap.put("wjId",wjId);
            wjDao.saveWjQuestion(tmap);
            Object obj = tmap.get("id");
            Integer quId = Integer.parseInt(obj.toString());
            saveSelectMap(tmap,quId);
        }

        System.out.println("rss========"+rs.toString());

    }

    /**
     * 获取规则对应的题目并保存
     * @param tId  规则Id
     * @param tnum  题目总数
     * @return
     */
    private float getWjQuestion(int tId,int tnum,List<Map> rs) {
        List<Map> musts = wjDao.findMustBankIdByRuleId(tId);
        List<Map> randoms = wjDao.findRandomBankIdByRuleId(tId);
        float maxSum = 0f;
        maxSum = getQuestUtil(rs,musts,1,tnum);//获取必选题
        maxSum += getQuestUtil(rs,randoms,0,tnum);//获取随机体
        Map temp = rs.get(rs.size()-1);
        //保存到wj表中
        return maxSum;
    }

    /**
     * 获取题库下的题目并保存
     * @param rs
     * @param maps 题库Id集合
     * @param type 题库类型，必选还是随机
     * @param tnum 题目数量
     */
    private float getQuestUtil(List<Map> rs, List<Map> maps, int type, int tnum) {
        float maxScoreSum = 0f;//最大分值和
        int lackNum = 0;//必选题中缺少数量
        for(int i=0; i<maps.size(); i++){
            Map temp = maps.get(i);
            Integer bankId = (Integer) temp.get("bankId");
            //对应题库下所有题目
            List<Map> quests = findQuestByBankId(bankId,type);
            Float per = (Float) temp.get("per");
            float tp =  (per/100f);
            int quNum = (int)(tnum * tp);//需求数量
            int bankNum = quests.size();//题库数量
            //如果题库中的题目小于等于需求的题目数
            int startMax = 0;//随机起点最大值
            int start = 0;
            if(bankNum<quNum) {
                throw  new MyException((Constant.QUESTION_NOT_ENOUGH.getExplanation()));
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
        return  maxScoreSum;
    }

    private List<Map> findQuestByBankId(Integer bankId, int type) {
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
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return wjDao.findQuestionByBankId(map);
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
        Map tempA = wjDao.findSelectA(questionId);
        Map tempB = wjDao.findSelectB(questionId);
        Map tempC = wjDao.findSelectC(questionId);
        Map tempD = wjDao.findSelectD(questionId);
        Map tempE = wjDao.findSelectE(questionId);
        Map tempF = wjDao.findSelectF(questionId);
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

    /**
     * 获取问卷中的选项
     * @param temp
     * @param questionId
     */
    private void getWjQuestionSelect(Map temp, Integer questionId) {
        Map tempA = wjDao.findWjSelectA(questionId);
        Map tempB = wjDao.findWjSelectB(questionId);
        Map tempC = wjDao.findWjSelectC(questionId);
        Map tempD = wjDao.findWjSelectD(questionId);
        Map tempE = wjDao.findWjSelectE(questionId);
        Map tempF = wjDao.findWjSelectF(questionId);
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

    /**
     * 保存问卷选项
     * @param temp
     * @param questionId
     */
    public void saveSelectMap(Map temp,Integer questionId){
        Map selectMap = new HashMap();
        List<Map> selects = (List<Map>) temp.get("select");
        Integer count = selects.size();
        for(int i=65; i<65+count; i++){
            Map tmap = selects.get(i-65);
            tmap.put("belong_wj_qu",questionId);
            if(i==65)wjDao.saveWjSelectA(tmap);
            if(i==66)wjDao.saveWjSelectB(tmap);
            if(i==67)wjDao.saveWjSelectC(tmap);
            if(i==68)wjDao.saveWjSelectD(tmap);
            if(i==69)wjDao.saveWjSelectE(tmap);
            if(i==70)wjDao.saveWjSelectF(tmap);
        }
    }


    public static void main(String[] arg){
        String ss = "[2018-06-19T05: 01: 00.000Z,2018-06-22T17: 00: 00.000Z]";
        String s = "2018-06-19T05: 01: 00.000Z";
        String sss =  DateUtils.format(DateUtils.parse(s),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
        System.out.println("===="+sss);
    }



}
