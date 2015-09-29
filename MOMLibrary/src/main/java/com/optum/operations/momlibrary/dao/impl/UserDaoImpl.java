package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.optum.operations.momlibrary.dao.UserDao;
import com.optum.operations.momlibrary.entity.User;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User getUser(String lanId) {
		Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("lanID",lanId));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void addUser(User user) {
		persist(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>)criteria.list();
	}
	

}
