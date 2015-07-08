package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.PayrollPeriodDao;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
@Repository
public class PayrollPeriodDaoImpl  extends AbstractDao implements PayrollPeriodDao{

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
		persist(pp);
	}

}
