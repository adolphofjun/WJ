package com.pluten.base.service;

import java.util.List;
import java.util.Map;

public interface BankService {
    boolean exitBankNameOrCode(Map bank);

    void saveBank(Map bank);

    void updateBankState(Map bank);

    void deleteBank(Integer bankId);

    List<Map> findBankList(Map map);
}
