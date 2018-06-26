package com.pluten.base.service.impl;

import com.pluten.base.dao.QuestDao;
import com.pluten.base.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("questService")
public class QuestServiceImpl implements QuestService {

    @Autowired private QuestDao questDao;
    public void editQuest(Map map) {
        System.out.println("===="+map.toString());
        List selectList = (List) map.get("select");
        String quId =  map.get("id")+"";
        String quName = (String) map.get("name");
        questDao.editQuest(map);
        editSelect(quId,selectList);
    }
    public void editSelect(String quId,List list){
        for(int i=0; i<list.size(); i++){
            Map listItem = (Map) list.get(i);
            String title = (String) listItem.get("title");
            Map temp = new HashMap();
            temp.put("id",listItem.get("id"));
            temp.put("name",listItem.get("name"));
            temp.put("score",listItem.get("score"));
            temp.put("belong_qu",quId);
            if("A".equals(title)){
                questDao.editQuestSelectA(temp);
            }else if("B".equals(title)){
                questDao.editQuestSelectB(temp);
            }else if("C".equals(title)){
                questDao.editQuestSelectC(temp);
            }else if("D".equals(title)){
                questDao.editQuestSelectD(temp);
            }else if("E".equals(title)){
                questDao.editQuestSelectE(temp);
            }else if("F".equals(title)){
                questDao.editQuestSelectF(temp);
            }

        }
    }
}
