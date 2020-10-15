package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.account.Account;
import com.app.account.AccountStatus;
import com.app.account.AccountType;
import com.app.dao.AccountDAO;
import com.app.dao.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public int withdraw(double amount, int id, User user) throws BusinessException {
		int c=0;
		double total =0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT userId, balance FROM accounts where id = ?"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id);
				ResultSet resultSet=preparedStatement.executeQuery();
				resultSet.next();
				if(resultSet.getDouble("balance")<amount) {
					throw new BusinessException("Not enough funds");
				}
				total = resultSet.getDouble("balance")-amount;
				if(user.getRole().getRoleId()!=3 && user.getUserId() != resultSet.getInt("userId")){
						throw new BusinessException("You do not have access to this account");
				}}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update accounts set balance = ? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(2, id);
			preparedStatement.setDouble(1, total);
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("Account update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return c;
	}
	

	@Override
	public int deposit(double amount, int id, User user) throws BusinessException {
		int c=0;
		double total =0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT userId, balance FROM accounts where id = ?"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id);
				ResultSet resultSet=preparedStatement.executeQuery();
				resultSet.next();
				total = resultSet.getDouble("balance")+amount;
				if(user.getRole().getRoleId()!=3 && user.getUserId() != resultSet.getInt("userId")){
						throw new BusinessException("You do not have access to this account");
				}}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update accounts set balance = ? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(2, id);
			preparedStatement.setDouble(1, total);
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("Account update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return c;
	}

	@Override
	public int transfer(double amount, int id1, int id2, User user) throws BusinessException {
		int c=0;
		double total =0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT userId, balance FROM accounts where id = ?"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id1);
				ResultSet resultSet=preparedStatement.executeQuery();
				resultSet.next();
				if(resultSet.getDouble("balance")<amount) {
					throw new BusinessException("Not enough funds");
				}
				total = resultSet.getDouble("balance")-amount;
				if(user.getRole().getRoleId()!=3 && user.getUserId() != resultSet.getInt("userId")){
						throw new BusinessException("You do not have access to this account");
				}}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}		
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update accounts set balance = ? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(2, id1);
			preparedStatement.setDouble(1, total);
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("Account update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT userId, balance FROM accounts where id = ?"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id2);
				ResultSet resultSet=preparedStatement.executeQuery();
				resultSet.next();
				total = amount + resultSet.getDouble("balance");
				}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}		
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update accounts set balance = ? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(2, id2);
			preparedStatement.setDouble(1, total);
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("Account update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return c;
	}

	@Override
	public List<Account> findAccounts(User user) throws BusinessException {
		List<Account> AccountList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT id, userId, balance, state, type FROM accounts"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			
			if(user.getRole().getRoleId() != 1) {
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("id"));
				account.setBalance(resultSet.getInt("balance"));
				AccountType type = new AccountType(resultSet.getString("type"));
				account.setType(type);
				AccountStatus status = new AccountStatus(resultSet.getString("state"));
				account.setStatus(status);
				AccountList.add(account);
				
			}}
			else {
				String sql1 ="SELECT id, userId, balance, state, type FROM accounts where userId = ?";
				PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
				preparedStatement.setInt(1,user.getUserId());
				ResultSet resultSet=preparedStatement1.executeQuery();
				while(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getInt("id"));
					account.setBalance(resultSet.getInt("balance"));
					AccountType type = new AccountType(resultSet.getString("type"));
					account.setType(type);
					AccountStatus status = new AccountStatus(resultSet.getString("state"));
					account.setStatus(status);
					AccountList.add(account);
					
				}
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return AccountList;
	}

	@Override
	public List<Account> findAccountById(User user, int id) throws BusinessException {
		List<Account> AccountList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT id, userId, balance, state, type FROM accounts where id = ?"	;
			if(user.getRole().getRoleId()==1) {
				sql ="SELECT id, userId, balance, state, type FROM accounts where id = ? and userId=?";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id);
				if(user.getRole().getRoleId()==1) {
					preparedStatement.setInt(1,user.getUserId());
				}
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getInt("id"));
					account.setBalance(resultSet.getInt("balance"));
					AccountType type = new AccountType(resultSet.getString("type"));
					account.setType(type);
					AccountStatus status = new AccountStatus(resultSet.getString("state"));
//					System.out.println(resultSet.getString("state"));
//					System.out.println(status.getStatus());
//					status.setStatus(resultSet.getString("state"));
					System.out.println(status.getStatus()+ status.getStatusId());
					account.setStatus(status);
					AccountList.add(account);
					
				}
			


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return AccountList;
	}

	@Override
	public List<Account> findAccountByStatus(User user, AccountStatus status) throws BusinessException {
		List<Account> AccountList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			
			String sql ="SELECT id, userId, balance, state, type FROM accounts where state = ?";
			if(user.getRole().getRoleId()==1) {
				sql ="SELECT id, userId, balance, state, type FROM accounts where state = ? and userId = ?";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,status.getStatus());
				if(user.getRole().getRoleId()==1) {
					preparedStatement.setInt(2, user.getUserId());
				}
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getInt("id"));
					account.setBalance(resultSet.getInt("balance"));
					AccountType type = new AccountType(resultSet.getString("type"));
					account.setType(type);
					AccountStatus status1 = new AccountStatus(resultSet.getString("state"));
					account.setStatus(status1);
					AccountList.add(account);
					
				}
			


		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return AccountList;
	}

	@Override
	public List<Account> findAccountByUserId(User user) throws BusinessException {
		List<Account> AccountList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="SELECT id, userId, balance, state, type FROM accounts where userId = ?"	;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,user.getUserId());
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getInt("id"));
					account.setBalance(resultSet.getInt("balance"));
					AccountType type = new AccountType(resultSet.getString("type"));
					account.setType(type);
					AccountStatus status = new AccountStatus(resultSet.getString("state"));
					account.setStatus(status);
					AccountList.add(account);
					
				}
			


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return AccountList;
	}

	@Override
	public int submitAccount(User user, AccountType type) throws BusinessException {
		int b;
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="insert into accounts( userId, balance, state, type) values(?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setDouble(2, 0);
			preparedStatement.setString(3, "Pending");
			preparedStatement.setString(4, type.getType());
			
			b=preparedStatement.executeUpdate();
			if(b==0) {
				throw new BusinessException("Account creation failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return b;
	}
	

	@Override
	public int updateAccount(int userId, Account account) throws BusinessException {
		int c=0;
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update accounts set userId= ?, balance = ?,state =?,type =? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setDouble(2, account.getBalance());
			preparedStatement.setString(3, account.getStatus().getStatus());
			preparedStatement.setString(4, account.getType().getType());
			preparedStatement.setInt(5, account.getAccountId());
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("Account update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return c;
	}
}
