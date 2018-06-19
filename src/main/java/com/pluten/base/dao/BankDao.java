package com.pluten.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BankDao {
    void saveBank(Map map);

    List<Map> findBankList(Map map);

    Integer isCanChangeBankState(Integer bankId_);

    void updateBankState(Map map);

    void deleteBank(Integer bankId);
}
