package com.uhg.optum.ssmo.otnd.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.entity.IncomeType;
import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.service.IncomeTypeService;
import com.uhg.optum.ssmo.otnd.service.PayrollDetailsService;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;
import com.uhg.optum.ssmo.otnd.vo.PayrollPeriodVo;

@Controller
public class OTNDMainController {

	@Autowired
	private PayrollPeriodService periodService;

	@Autowired
	private IncomeTypeService incomeTypeService;

	@Autowired
	private PayrollDetailsService payrollDetailsService;

	private final static Logger logger = LoggerFactory
			.getLogger(OTNDMainController.class);

	@RequestMapping(value = "/getPayPeriods", method = RequestMethod.POST)
	public @ResponseBody List<PayrollPeriodVo> getPayPeriods() {
		List<PayrollPeriod> openPeriods = periodService.getAllPeriods();
		logger.debug("[getPayPeriods] fetched size: " + openPeriods.size());
		List<PayrollPeriodVo> vos = new ArrayList<PayrollPeriodVo>();
		for (PayrollPeriod period : openPeriods) {
			vos.add(new PayrollPeriodVo(period.getPeriod().toString(), period
					.getStatus()));
		}
		return vos;
	}

	@RequestMapping(value = "/getIncomeTypes", method = RequestMethod.POST)
	public @ResponseBody List<IncomeType> getIncomeTypes() {
		return incomeTypeService.getAllIncomeTypes();
	}

	@RequestMapping(value = "/getCodesByType", method = RequestMethod.POST)
	public @ResponseBody List<IncomeType> getCodesByType(
			@RequestParam String incomeType) {
		return incomeTypeService.getCodesByTypes(incomeType);
	}

	@RequestMapping(value = "/addPayrollDetails", method = RequestMethod.POST)
	public @ResponseBody String addPayrollDetails(
			@RequestParam String payPeriod, @RequestParam String incomeType,
			@RequestParam String incomeCode, @RequestParam String detailValue,
			@RequestParam String remarks) {
		logger.debug("[addPayrollDetails] period: " +payPeriod);
		String[] localDate = payPeriod.split("-");
		PayrollPeriod pp = periodService.getPayroll(new PayrollPeriod(
				new LocalDate(Integer.parseInt(localDate[0]),
						Integer.parseInt(localDate[1]),
						Integer.parseInt(localDate[2]))));
		String uname = System.getProperty("user.name");
		PayrollDetails payDetails = new PayrollDetails(new Employee(uname), new IncomeType(incomeCode),
				detailValue, remarks, new LocalDate(),pp);
		payrollDetailsService.savePayrollDetail(payDetails);
		return "SUCCESS";
	}

	@RequestMapping(value = "/getIncomeDetails", method = RequestMethod.POST)
	public @ResponseBody List<PayrollDetails> getIncomeDetails(
			@RequestParam String payPeriod,@RequestParam String isAdmin) {
		logger.debug("[getIncomeDetails] getting payroll income details: " +isAdmin);
		String[] localDate = payPeriod.split("-");
		PayrollPeriod pp = periodService.getPayroll(new PayrollPeriod(
				new LocalDate(Integer.parseInt(localDate[0]),
						Integer.parseInt(localDate[1]),
						Integer.parseInt(localDate[2]))));
		PayrollDetails detail = new PayrollDetails();
		detail.setPayrollPeriod(pp);
		if("true".equals(isAdmin)){
			detail.setEmpId(null);
		}else{
			detail.setEmpId(new Employee(System.getProperty("user.name")));
		}
		return payrollDetailsService.getPayrollDetails(detail);
	};
	
	@RequestMapping(value = "/deleteIncomeDetail", method = RequestMethod.POST)
	public @ResponseBody String deleteIncomeDetail(
			@RequestParam String incomeID) {
		logger.debug("[deleteIncomeDetail] deleting payroll income detail: " +incomeID);
		payrollDetailsService.deletePayroll(Integer.parseInt(incomeID));
		return "SUCCESS";
	}
	
	
}
