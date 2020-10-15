package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.UpdateUserDAO;
import com.app.dao.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.User;

public class UpdateUserDAOImpl implements UpdateUserDAO {

	@Override
	public int updateUser(User user) throws BusinessException {
		int c=0;
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="update users set id=?, username = ?, password =?, firstname =?, lastname= ?, email=?,role=? where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getFirstName());
			preparedStatement.setString(5, user.getLastName());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setInt(7, user.getRole().getRoleId());
			preparedStatement.setInt(8, user.getUserId());
			c=preparedStatement.executeUpdate();
			if(c==0) {
				throw new BusinessException("User update failed... Please try again");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //for test
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN!!!!!....");
		}
		return c;
	}

}
