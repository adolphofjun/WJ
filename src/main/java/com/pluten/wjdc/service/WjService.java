package com.pluten.wjdc.service;

import java.util.List;
import java.util.Map;

/**
 * 问卷新建和问卷获取
 */
public interface WjService {


    public void newWj(Map map);

    public List<Map> findWjTitleList();

    public Map findQuestByWjId(Integer wjId);


}
