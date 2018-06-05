package com.pluten.base.service.impl;

import com.pluten.base.dao.BaseDao;
import com.pluten.base.service.BaseService;
import com.pluten.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseDao baseDao;

    public void saveBank(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        baseDao.saveBank(map);
    }

    public List<Map> findBank(Map map) {
        return null;
    }

    public void updateBank(Map map) {

    }

    public boolean exitBankNameOrCode(Map map) {
        Integer is = baseDao.exitBankNameOrCode(map);
        if(is!=0) return  true;
        else return false;
    }

    public void deleteBank(Integer id) {
        baseDao.deleteBank(id);
    }

    public List<Map> findBankList(Map map) {
        return baseDao.findBankList(map);
    }
}
