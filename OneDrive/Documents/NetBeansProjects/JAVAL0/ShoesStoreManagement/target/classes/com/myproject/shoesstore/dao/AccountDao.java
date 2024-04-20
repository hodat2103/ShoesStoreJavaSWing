package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Account;

/**
 *
 * @author Tadaboh;
 */
public interface AccountDao {
    public Account login(String username, String password);
}
