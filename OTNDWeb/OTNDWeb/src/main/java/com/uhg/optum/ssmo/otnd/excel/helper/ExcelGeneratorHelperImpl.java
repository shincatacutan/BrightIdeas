package com.uhg.optum.ssmo.otnd.excel.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class ExcelGeneratorHelperImpl {

	public static List<VariableInputReport> consolidate(
			List<PayrollDetails> details, String code) {
		List<VariableInputReport> list = new ArrayList<VariableInputReport>();
		Map<String, VariableInputReport> map = new HashMap<String, VariableInputReport>();

		for (PayrollDetails payrollDetail : details) {
			if (code.equals(payrollDetail.getIncomeType().getType())) {
				VariableInputReport inputReport = new VariableInputReport();
				inputReport.setEmpId(payrollDetail.getEmpId().getEmpID());
				inputReport.setEmpName(payrollDetail.getEmpId().getFullName());
				inputReport.setSegment("OPTUM");
				inputReport.setProcess("TECHNOLOGY");
				inputReport.setOtndCode(payrollDetail.getIncomeType().getId());
				inputReport.setHours(payrollDetail.getProdHrsAmt());
				inputReport.setAmount(payrollDetail.getProdHrsAmt());
				inputReport.setRemarks(payrollDetail.getRemarks());
				VariableInputReport key = map.get(payrollDetail.getIncomeType()
						.getId());
				if (key == null) {
					map.put(payrollDetail.getIncomeType().getId(), inputReport);
				} else {
					BigDecimal origHours = new BigDecimal(key.getHours());
					BigDecimal hoursToAdd = new BigDecimal(payrollDetail.getProdHrsAmt());
					key.setHours(origHours.add(hoursToAdd).toString());
					
					BigDecimal origAmt = new BigDecimal(key.getAmount());
					BigDecimal amtToAdd = new BigDecimal(payrollDetail.getProdHrsAmt());
					key.setAmount(origAmt.add(amtToAdd).toString());
					
					key.setRemarks(key.getRemarks() +", "+payrollDetail.getRemarks());
				}
			}

		}
		
		for(Entry<String, VariableInputReport> entry: map.entrySet()){
			list.add(entry.getValue());
		}
		return list;
	}

}
