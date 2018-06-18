package com.pluten.base.service.impl;

import com.pluten.base.dao.BaseDao;
import com.pluten.base.service.BaseService;
import com.pluten.utils.Constant;
import com.pluten.utils.DateUtils;
import com.pluten.utils.Globel;
import com.pluten.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("WjdcServiceImpl");
    @Autowired
    private BaseDao baseDao;

    public void saveBank(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        baseDao.saveBank(map);
    }

    public List<Map> findBank(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
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
        Integer isUser = baseDao.isCanChangeBankState(id);
        if(isUser==0 || isUser==null)
        baseDao.deleteBank(id);
        else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
    }

    public void deleteDept(Integer id) {
        Integer isUser = baseDao.isCanChangeDeptState(id);
        logger.info("========"+isUser);
        if(isUser==0)
            baseDao.deleteDept(id);
        else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
    }

    public List<Map> findBankList(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return baseDao.findBankList(map);
    }

    public void updateBankState(Map map) {
        String bankId =  map.get("id")+"";
        if(null == bankId || "".equals(bankId)){
            throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
        }else{
            Integer bankId_ = Integer.parseInt(bankId);
            Integer isUser = baseDao.isCanChangeBankState(bankId_);
            if(isUser==0 || isUser==null)
                baseDao.updateBankState(map);
            else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    public void saveDept(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        baseDao.saveDept(map);
    }

    public boolean exitDeptNameOrCode(Map map) {
        Integer is = baseDao.exitDeptNameOrCode(map);
        if(is!=0) return  true;
        else return false;
    }

    public List<Map> findDeptList(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return baseDao.findDeptList(map);
    }

    public List<Map> findUserOfRoleAndDept(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return baseDao.findUserOfRoleAndDept(map);
    }

    public List<Map> findUserOfRoleAndDept(Integer deptId, Integer roleId) {
        Map map = new HashMap();
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        map.put("deptId",deptId);
        map.put("roleId",roleId);
        return baseDao.findUserOfRoleAndDept(map);
    }

    public void updateDeptState(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        String deptId = (String) map.get("id");
        if(null == deptId || "".equals(deptId)){
            throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
        }else{
            Integer deptId_ = Integer.parseInt(deptId);
            Integer isUser = baseDao.isCanChangeDeptState(deptId_);
            if(isUser==0)
                baseDao.updateDeptState(map);
            else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }
}
