package com.example.test.service.Impl;

import com.example.test.dao.UserDAO;
import com.example.test.model.UserModel;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserModel printUser()
	{
		UserModel user =  dao.getUser();
		
		return user;
	}
}
