package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.optum.operations.momlibrary.dao.CategoryDao;
import com.optum.operations.momlibrary.entity.Category;
@Repository
public class CategoryDaoImpl extends AbstractDao implements CategoryDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		Criteria criteria = getSession().createCriteria(Category.class);
		return (List<Category>) criteria.list();
	}

}
