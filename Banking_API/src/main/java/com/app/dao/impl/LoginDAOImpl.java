package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.LoginDAO;
import com.app.dao.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Role;
import com.app.model.User;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean isValidUser(User user) throws BusinessException {
		boolean b = false;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql ="select id, username, role from users where username = ? and password = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				Role role = new Role(resultSet.getInt(3));
				user.setRole(role);
				user.setUserId(resultSet.getInt(1));
				b=true;
			}else {
				throw new BusinessException("Invalid Login Credentials");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured... Kindly Contact System Admin");
		}

		return b;
	}

}
