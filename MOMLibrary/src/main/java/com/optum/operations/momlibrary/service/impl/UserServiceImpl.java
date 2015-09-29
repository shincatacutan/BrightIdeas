package com.optum.operations.momlibrary.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optum.operations.momlibrary.dao.UserDao;
import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userdao;

	@Override
	public User getUser(String lanId) {
		logger.debug("[service layer] getUser : " + lanId);
		return userdao.getUser(lanId);
	}

	@Override
	public void addUser(User user) {
		userdao.addUser(user);

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAllUsers() {
		return userdao.getAllUsers();
	}

}
