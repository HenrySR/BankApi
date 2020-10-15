package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface UpdateUserDAO {

	public int updateUser(User user) throws BusinessException;
}
