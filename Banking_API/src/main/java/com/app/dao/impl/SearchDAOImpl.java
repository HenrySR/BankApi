package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.SearchDAO;
import com.app.dao.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public class SearchDAOImpl implements SearchDAO {

	@Override
	public List<User> searchId(int id) throws BusinessException {
		List<User> usersList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, firstname, lastname, email, role from users where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				User user=new User();
				user.setUserId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getInt("role"));
				user.setRole(role);
				usersList.add(user);
				
			}
			else {
				throw new BusinessException("No customers with id "+id+" in the DB");		
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return usersList;
	}

	@Override
	public List<User> searchUsername(String username) throws BusinessException{
		List<User> usersList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, firstname, lastname, email, role from users where username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,username);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				User user=new User();
				user.setUserId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getInt("role"));
				user.setRole(role);
				usersList.add(user);
				
			}
			else {
				throw new BusinessException("No customers with username "+username+" in the DB");		
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return usersList;
	}

	@Override
	public List<User> searchFname(String fname) throws BusinessException{
		List<User> usersList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, firstname, lastname, email, role from users where firstname = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,fname);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				User user=new User();
				user.setUserId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getInt("role"));
				user.setRole(role);
				usersList.add(user);
				
			}
			else {
				throw new BusinessException("No customers with firstname "+fname+" in the DB");		
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return usersList;
	}

	@Override
	public List<User> searchLname(String lname) throws BusinessException{
		List<User> usersList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, firstname, lastname, email, role from users where lastname = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,lname);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				User user=new User();
				user.setUserId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getInt("role"));
				user.setRole(role);
				usersList.add(user);
				
			}
			else {
				throw new BusinessException("No customers with lastname "+lname+" in the DB");		
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return usersList;
	}

	@Override
	public List<User> allUsers() throws BusinessException {
		List<User> usersList=new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, firstname, lastname, email, role from users";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user=new User();
				user.setUserId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				Role role = new Role(resultSet.getInt("role"));
				user.setRole(role);
				usersList.add(user);
				
			}


		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}
		return usersList;
	}

}
