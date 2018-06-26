package com.pluten.wjdc.service.impl;

import com.pluten.utils.Constant;
import com.pluten.utils.DateUtils;
import com.pluten.utils.MyException;
import com.pluten.wjdc.dao.WjDao;
import com.pluten.wjdc.dao.WjJfDao;
import com.pluten.wjdc.service.WjJfService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷计分
 */
@Service("wjJfService")
public class WjJfServiceImpl implements WjJfService {
    @Autowired
    private WjJfDao wjJfDao;
    @Autowired private WjDao wjDao;
    public void saveEveryOneRecord(Map map) {
        List list = (List) map.get("data");
        float score = getSumScore(list);
        map.put("score",score);
        map.put("answerTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        wjJfDao.saveEveryOneRecord(map);
        map.put("isDelete",1);
        wjDao.updateWjTitleIsDelete(map);
    }

    public void saveSumRecord(Map map) {
        List<Map> maps = wjJfDao.findAnswerRecord(map);
        float sum = getSumScore(maps);
        map.put("score",sum);
        map.put("answerTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        wjJfDao.saveSumRecord(map);
    }

    public List<Map> findAnswerRecord(Map map) {
        return wjJfDao.findAnswerRecord(map);
    }

    public List<Map> findAnswerRecordSum(Map map) {
        return wjJfDao.findAnswerRecordSum(map);
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
}
