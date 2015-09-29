package com.optum.operations.momlibrary.dao;

import java.util.List;

import com.optum.operations.momlibrary.entity.User;

public interface UserDao {
	public User getUser(String lanId);
	public void addUser (User user);
	public void updateUser (User user);
	public void deleteUser (User user);
	public List<User> getAllUsers();
}
