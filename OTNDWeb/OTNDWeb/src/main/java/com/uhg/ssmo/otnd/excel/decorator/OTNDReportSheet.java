package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class OTNDReportSheet implements ReportSheet {

	@Override
	public void generate(HSSFWorkbook workbook, List<VariableInputReport> items) {
		HSSFSheet otnd = workbook.createSheet("OT_ND");
		int rownum = 0;
		Row header = otnd.createRow(rownum++);
		String headers[] = { "EMPLOYEE ID#", "EMPLOYEE NAME", "SEGMENT",
				"PROCESS", "OT_ND CODE", "BILLABLE", "NON-BILLABLE", "HOURS", "REMARKS",
				"REASON FOR NON-PROD HOURS", "BUSINESS SPOC NAME" };
		int headerCtr = 0;
		headerCtr = ReportSheetUtils.createHeader(workbook,header, headers, headerCtr);
		
		for (VariableInputReport item : items) {
			int cellCtr = 0;
			Row row = otnd.createRow(rownum++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getEmpId(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getEmpName(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getSegment(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getProcess(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getOtndCode(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getBillable(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getNonBillable(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getHours(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getRemarks(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getNonProdReason(), cellCtr++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getBusSpocName(), cellCtr++);
		}
		ReportSheetUtils.autoSizeWidth(otnd, headers);
	}

	
}
