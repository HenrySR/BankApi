package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface LoginDAO {
	
	public boolean isValidUser(User user) throws BusinessException;

}
