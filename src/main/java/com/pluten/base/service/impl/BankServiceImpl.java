package com.pluten.base.service.impl;

import com.pluten.base.dao.BankDao;
import com.pluten.base.service.BankService;
import com.pluten.utils.Constant;
import com.pluten.utils.DateUtils;
import com.pluten.utils.Globel;
import com.pluten.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bankService")
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;
    public boolean exitBankNameOrCode(Map bank) {
        return false;
    }

    public void saveBank(Map map) {
        if(!map.containsKey("creator")) map.put("creator","1");
        map.put("creatorTime", DateUtils.format(DateUtils.getNowDate(),DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
        bankDao.saveBank(map);
    }

    public void updateBankState(Map map) {
        String bankId =  map.get("id")+"";
        if(null == bankId || "".equals(bankId)){
            throw  new MyException(Constant.ARGUMENT_EXCEPTION.getExplanation());
        }else{
            Integer bankId_ = Integer.parseInt(bankId);
            Integer isUser = bankDao.isCanChangeBankState(bankId_);
            if(isUser==0 || isUser==null)
                bankDao.updateBankState(map);
            else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
        }
    }

    public void deleteBank(Integer bankId) {
        Integer isUser = bankDao.isCanChangeBankState(bankId);
        if(isUser==0 || isUser==null)
            bankDao.deleteBank(bankId);
        else throw  new MyException(Constant.STATE_IS_NOT_VARIBALE.getExplanation());
    }

    public List<Map> findBankList(Map map) {
        if(map==null) map = new HashMap();
        map.put(Globel.SQL_TYPE_KEY,Globel.SQL_TYPE_VLUE);
        return bankDao.findBankList(map);
    }
}
