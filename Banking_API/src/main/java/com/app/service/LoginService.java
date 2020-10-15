package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface LoginService {

	public boolean isValidUser(User user) throws BusinessException;
}
