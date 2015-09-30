package com.optum.operations.momlibrary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.entity.Category;
import com.optum.operations.momlibrary.entity.MomUpdate;
import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.MoMService;
import com.optum.operations.momlibrary.service.UserService;
import com.optum.operations.momlibrary.util.MomUtils;
import com.optum.operations.momlibrary.vo.MomVO;

@Controller
public class BaseMoMController {

	@Autowired
	private MoMService momService;

	@Autowired
	private UserService userService;

	private final static Logger logger = LoggerFactory
			.getLogger(BaseMoMController.class);

	private static final String SUCCESS = "200";

	@RequestMapping(value = "/searchMOM", method = RequestMethod.POST)
	public @ResponseBody List<MomVO> searchMOM() {
		// logger.debug("[searchMOM] title: "+title);
		List<MomUpdate> updates = momService.searchMOM(new MomUpdate());
		List<MomVO> results = new ArrayList<MomVO>();
		for (MomUpdate update : updates) {
			MomVO vo = new MomVO();
			// vo.setAuthor(update.getAuthor());
			vo.setCascadedDate(update.getCascadedDate().toString());
			vo.setCategoryName(update.getCategory().getName());
			vo.setDetailedInfo(update.getDetailedInfo());
			vo.setId(update.getId());
			vo.setLink(update.getLink());
			vo.setStatus(update.getStatus());
			vo.setTitle(update.getTitle());
			vo.setCreateDate(update.getCreateDate().toString());
			vo.setUpdateDate(update.getUpdateDate().toString());
			vo.setAuthor(update.getAuthor().getLanID());
			vo.setUploader(update.getUploader().getLanID());
			results.add(vo);
		}

		logger.debug("updates size: " + updates.size());
		return results;
	}

	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public @ResponseBody String addUpdate(@RequestParam String category,
			@RequestParam String dateCascaded,
			@RequestParam String detailedInfo, @RequestParam String status,
			@RequestParam String link, @RequestParam String author,
			@RequestParam String title, HttpServletRequest request) {
		// TODO Auto-generated method stub
		MomUpdate update = new MomUpdate();
		User user = userService.getUser(author);
		update.setAuthor(user);
		update.setCascadedDate(MomUtils.dateParser(dateCascaded));
		Category cat = new Category();
		cat.setId(Integer.parseInt(category));
		update.setCategory(cat);
		update.setCreateDate(new LocalDate());
		update.setDetailedInfo(detailedInfo);
		update.setLink(link);
		update.setStatus(status);
		update.setTitle(title);
		update.setUpdateDate(new LocalDate());
		User loggedUser = (User) request.getSession().getAttribute("employee");
		logger.debug("[addUpdate] loggedUser: " + loggedUser.toString());
		update.setUploader(loggedUser);
		momService.addMoM(update);
		
		return SUCCESS;
	}

}