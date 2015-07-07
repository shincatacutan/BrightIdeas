package com.optum.operations.momlibrary.service;

import com.optum.operations.momlibrary.entity.User;

public interface UserService {
	public User getUser(String lanID);
	public void addUser (User user);
	public void updateUser (User user);
	public void deleteUser (User user);
}
