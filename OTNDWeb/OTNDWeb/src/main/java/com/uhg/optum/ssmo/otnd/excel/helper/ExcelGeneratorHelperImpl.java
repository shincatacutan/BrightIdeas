package com.uhg.optum.ssmo.otnd.excel.helper;

import java.util.ArrayList;
import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class ExcelGeneratorHelperImpl {

	public static List<VariableInputReport> consolidate(
			List<PayrollDetails> details, String code) {
		List<VariableInputReport> list = new ArrayList<VariableInputReport>();
		
		for (PayrollDetails payrollDetail : details) {
			if(code.equals(payrollDetail.getIncomeType().getType())){
				VariableInputReport inputReport = new VariableInputReport();
				inputReport.setEmpId(payrollDetail.getEmpId().getEmpID());
				inputReport.setEmpName(payrollDetail.getEmpId().getFullName());
				inputReport.setSegment("OPTUM");
				inputReport.setProcess("TECHNOLOGY");
				inputReport.setOtndCode(payrollDetail.getIncomeType().getId());
				inputReport.setHours(payrollDetail.getProdHrsAmt());

				list.add(inputReport);
			}
			
		}
		return list;
	}

}
