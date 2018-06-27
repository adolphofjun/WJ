package com.pluten.wjdc.service.impl;

import com.pluten.base.dao.RuleDao;
import com.pluten.utils.Globel;
import com.pluten.wjdc.dao.WjDao;
import com.pluten.wjdc.service.WjReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("wjReportService")
public class WjReportServiceImpl  implements WjReportService {

    @Autowired
    private WjDao wjDao;
    @Autowired
    private RuleDao ruleDao;

    public List<Map> findWjTitleReportList() {
        Map map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("visibility",1);
        return wjDao.findWjTitleList(map);
    }
}
