package com.app.service.impl;

import java.sql.ResultSet;
import java.util.List;

import com.app.dao.SearchDAO;
import com.app.dao.impl.SearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;
import com.app.service.Search;

public class SearchImpl implements Search {

	@Override
	public List<User> searchId(int id) throws BusinessException{
		SearchDAO search = new SearchDAOImpl();
		List<User> userList = search.searchId(id);
		for(User u:userList) {
			System.out.println(u.toString());
		}
		return userList;
		
	}

	@Override
	public List<User> searchString(String input) throws BusinessException{
		SearchDAO search = new SearchDAOImpl();
		List<User> userList = search.searchFname(input);
		return userList;
	}

}
