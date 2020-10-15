package com.app.service.impl;

import com.app.dao.LoginDAO;
import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;
import com.app.service.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean isValidUser(User user) throws BusinessException {
		LoginDAO dao = new LoginDAOImpl();
		boolean b = false;
		
		if(user!=null && user.getUsername()!=null && user.getPassword()!=null /*&& user.getUsername().matches("^[A-Za-z0-9]([._])|[A-Za-z0-9]{4,18}[A-Za-z0-9]") && user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$")*/) {
			b=dao.isValidUser(user);
			
		}else {
			throw new BusinessException("Invalid Login Credentials");
		}
		return b;
	
	}

}
