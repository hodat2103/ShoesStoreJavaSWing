package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.AccountDao;
import com.myproject.shoesstore.dao.DaoImpl.AccountDaoImpl;
import com.myproject.shoesstore.model.Account;
import com.myproject.shoesstore.service.AccountService;

/**
 *
 * @author Tadaboh;
 */
public class AccountServiceImpl implements AccountService{
    AccountDao accountDao = null;

    public AccountServiceImpl() {
        this.accountDao = new AccountDaoImpl();
    }
    
    @Override
    public Account login(String username, String password) {
        return accountDao.login(username, password);
    }
    
}
