package com.optum.operations.momlibrary.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.entity.Category;
import com.optum.operations.momlibrary.service.CategoryService;

@Controller
public class CategoryController {
	private final static Logger logger = LoggerFactory
			.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/getCategories", method = RequestMethod.POST)
	public @ResponseBody List<Category> getAllInquiry() {
		logger.debug("[getCategories]");
		return (List<Category>) categoryService.getAll();
	}

}
