package com.pluten.base.service.impl;

import com.pluten.base.dao.DeptDao;
import com.pluten.base.service.DeptService;
import com.pluten.utils.Constant;
import com.pluten.utils.DateUtils;
import com.pluten.utils.Globel;
import com.pluten.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("DeptServiceImpl");
    @Autowired
    private DeptDao deptDao;
    public boolean exitDeptNameOrCode(Map map) {
        Integer is = deptDao.exitDeptNameOrCode(map);
        if(is!=0) return  true;
        else return false;
    }

    public void saveDept(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        deptDao.saveDept(map);
    }

    public void updateDeptState(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        String deptId = (String) map.get("id");
        if(null == deptId || "".equals(deptId)){
            throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
        }else{
            Integer deptId_ = Integer.parseInt(deptId);
            Integer isUser = deptDao.isCanChangeDeptState(deptId_);
            if(isUser==0)
                deptDao.updateDeptState(map);
            else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    public void deleteDept(Integer id) {
        Integer isUser = deptDao.isCanChangeDeptState(id);
        logger.info("========"+isUser);
        if(isUser==0)
            deptDao.deleteDept(id);
        else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
    }

    public List<Map> findDeptList(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return deptDao.findDeptList(map);
    }
}
