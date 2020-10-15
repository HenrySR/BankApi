package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.RegisterDAO;
import com.app.dao.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public class RegisterDAOImpl implements RegisterDAO {

	@Override
	public int registerUser(User user) throws BusinessException {
		int b;
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="insert into users(username, password, firstname, lastname, email, role) values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setInt(6, user.getRole().getRoleId());
			
			b=preparedStatement.executeUpdate();
			if(b==0) {
				throw new BusinessException("Customer Registration failed... Please try again");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		catch(SQLException e) {
			throw new BusinessException("Invalid Username already in use");
		}
		return b;
	}

}
