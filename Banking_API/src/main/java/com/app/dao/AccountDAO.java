package com.app.dao;

import java.util.List;

import com.app.account.Account;
import com.app.account.AccountStatus;
import com.app.account.AccountType;
import com.app.exception.BusinessException;
import com.app.model.User;

public interface AccountDAO {

	public int withdraw(double amount, int id, User user) throws BusinessException;
	
	public int deposit(double amount, int id, User user) throws BusinessException;
	
	public int transfer(double amount, int id1, int id2, User user) throws BusinessException;
	
	public List<Account> findAccounts(User user) throws BusinessException;
	
	public List<Account> findAccountById(User user, int id) throws BusinessException;
	
	public List<Account> findAccountByStatus(User user, AccountStatus status) throws BusinessException;
	
	public List<Account> findAccountByUserId(User user) throws BusinessException;
	
	public int submitAccount(User user, AccountType type) throws BusinessException;
	
	public int updateAccount(int userId, Account account) throws BusinessException;
}
