package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;

public interface PayrollPeriodService {
	public PayrollPeriod getPayroll(PayrollPeriod pp);
	public List<PayrollPeriod> getPayroll(String status);
	public void updatePayrollStatus(PayrollPeriod pp);
	public void deletePayrollPeriod(PayrollPeriod pp);
	public void addPayroll (PayrollPeriod pp);
}
