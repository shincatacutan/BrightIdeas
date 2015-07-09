package com.uhg.optum.ssmo.otnd.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhg.optum.ssmo.otnd.entity.IncomeType;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.service.IncomeTypeService;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;
import com.uhg.optum.ssmo.otnd.vo.PayrollPeriodVo;

@Controller
public class OTNDMainController {
	
	@Autowired
	private PayrollPeriodService periodService;
	
	@Autowired
	private IncomeTypeService incomeTypeService;

	private final static Logger logger = LoggerFactory
			.getLogger(OTNDMainController.class);
	
	@RequestMapping(value = "/getPayPeriods", method = RequestMethod.POST)
	public @ResponseBody List<PayrollPeriodVo> getPayPeriods() {
		List<PayrollPeriod> openPeriods = periodService.getAllPeriods();
		logger.debug("[getPayPeriods] fetched size: "+openPeriods.size());
		List<PayrollPeriodVo> vos = new ArrayList<PayrollPeriodVo>();
		for(PayrollPeriod period: openPeriods){
			vos.add(new PayrollPeriodVo(period.getPeriod().toString(), period.getStatus()));
		}
		return vos;
	}
	
	@RequestMapping(value = "/getIncomeTypes", method = RequestMethod.POST)
	public @ResponseBody List<IncomeType> getIncomeTypes() {
		return incomeTypeService.getAllIncomeTypes();
	}

	@RequestMapping(value = "/getCodesByType", method = RequestMethod.POST)
	public @ResponseBody List<IncomeType> getCodesByType(@RequestParam String incomeType) {
		return incomeTypeService.getCodesByTypes(incomeType);
	}
}
