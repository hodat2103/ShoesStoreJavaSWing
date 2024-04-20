package com.myproject.shoesstore.model;

/**
 *
 * @author Tadaboh;
 */
public class Account {
    private int accountCode;
    private String username;
    private String password;

    public Account() {
    }

    public Account(int accountCode,String username, String password) {
        this.accountCode = accountCode;
        this.username = username;
        this.password = password;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
