package com.pluten.wjdc.service;

import java.util.List;
import java.util.Map;

/**
 * 问卷新建和问卷获取
 */
public interface WjService {


    public void newWj(Map map);

    public List<Map> findWjTitleList();

    /**
     * 查询启用的问卷
     * @return
     */
    public List<Map> findWjTitleUsableList();

    public Map findQuestByWjId(Integer wjId);
    /**
     * 判断是否已经答题过
     * @param map  wjId targetId  answerId
     */
    Integer isExisted(Map map);
    /**
     * 查询问卷对应的问卷对象
     * @param wjId
     * @return
     */
    List<Map> findWjTarget(Integer wjId);


}
