package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public interface ReportSheet {
	public void generate(HSSFWorkbook workbook, List<VariableInputReport> items	);
}
