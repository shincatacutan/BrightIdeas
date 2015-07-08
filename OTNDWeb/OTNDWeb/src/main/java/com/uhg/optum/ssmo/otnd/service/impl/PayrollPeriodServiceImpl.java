package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;
@Service
@Transactional
public class PayrollPeriodServiceImpl implements PayrollPeriodService {

	@Autowired
	private PayrollPeriodService periodService;

	@Override
	public PayrollPeriod getPayroll(PayrollPeriod pp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayrollPeriod> getPayroll(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePayrollStatus(PayrollPeriod pp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePayrollPeriod(PayrollPeriod pp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPayroll(PayrollPeriod pp) {
		periodService.addPayroll(pp);
	}

}
