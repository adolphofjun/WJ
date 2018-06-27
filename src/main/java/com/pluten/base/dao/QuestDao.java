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
    void deleteQuestSelectA(Integer quId);
    void deleteQuestSelectB(Integer quId);
    void deleteQuestSelectC(Integer quId);
    void deleteQuestSelectD(Integer quId);
    void deleteQuestSelectE(Integer quId);
    void deleteQuestSelectF(Integer quId);
    void deleteQuestSelectG(Integer quId);
    void deleteQuestion(Integer quId);
}
