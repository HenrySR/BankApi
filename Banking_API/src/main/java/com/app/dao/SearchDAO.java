package com.app.dao;

import java.sql.ResultSet;
import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface SearchDAO {

	public List<User> searchId(int id) throws BusinessException;
	
	public List<User> searchUsername(String username) throws BusinessException;
	
	public List<User> searchFname(String fname) throws BusinessException;
	
	public List<User> searchLname(String fname) throws BusinessException;
	
	public List<User> allUsers() throws BusinessException;
}
