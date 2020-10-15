package com.app.service;

import java.sql.ResultSet;
import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface Search {

	public List<User> searchId(int id) throws BusinessException;
	
	public List<User> searchString(String input) throws BusinessException;
	
	
}
