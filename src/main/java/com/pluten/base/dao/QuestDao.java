package com.pluten.base.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface QuestDao {
    void editQuest(Map map);
    void editQuestSelectA(Map map);
    void editQuestSelectB(Map map);
    void editQuestSelectC(Map map);
    void editQuestSelectD(Map map);
    void editQuestSelectE(Map map);
    void editQuestSelectF(Map map);
    void editQuestSelectG(Map map);
    void editQuestSelectH(Map map);
    void deleteQuestSelectA(Map map);
    void deleteQuestSelectB(Map map);
    void deleteQuestSelectC(Map map);
    void deleteQuestSelectD(Map map);
    void deleteQuestSelectE(Map map);
    void deleteQuestSelectF(Map map);
    void deleteQuestSelectG(Map map);
    void deleteQuestSelectH(Map map);
}
