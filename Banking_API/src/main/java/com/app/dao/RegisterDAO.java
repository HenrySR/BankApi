package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface RegisterDAO {
	
	public int registerUser(User user) throws BusinessException;
}
