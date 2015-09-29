package com.optum.operations.momlibrary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optum.operations.momlibrary.dao.CategoryDao;
import com.optum.operations.momlibrary.entity.Category;
import com.optum.operations.momlibrary.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

}
