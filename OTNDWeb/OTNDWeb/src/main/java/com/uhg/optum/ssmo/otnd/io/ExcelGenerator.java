package com.uhg.optum.ssmo.otnd.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;
import com.uhg.optum.ssmo.otnd.excel.helper.ExcelGeneratorHelperImpl;
import com.uhg.ssmo.otnd.excel.decorator.OTNDReportSheet;
import com.uhg.ssmo.otnd.excel.decorator.ReportSheet;

public class ExcelGenerator {
	public String generate(List<PayrollDetails> details) {
		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet otnd = workbook.createSheet("OT_ND");
	

		List<VariableInputReport> items = ExcelGeneratorHelperImpl.consolidate(details, "OT_ND");
		ReportSheet otndSheet = new OTNDReportSheet();
		otndSheet.generate(workbook, items);
		
		HSSFSheet earnings = workbook.createSheet("EARNINGS");
		HSSFSheet taxableMeal = workbook.createSheet("TAXABLE MEAL");
		HSSFSheet lwop = workbook.createSheet("LWOP");
		HSSFSheet tardy = workbook.createSheet("TARDY");
		
		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString()
				.replace(".", "-").replace(":", "-").replace(" ", "_")
				.replace("-", "");
		String fileName = "OTND_" + timestamp+".xls";
		String path = "C:\\OTND\\";
		try {

			File file = new File(path);
			file.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(path + ""
					+ fileName));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}



//	private int addBodyDetails(List<PayrollDetails> details, HSSFSheet otnd,
//			int rownum) {
//		for(PayrollDetails detail:details){
//			int cellCtr=0;
//			Row row = otnd.createRow(rownum++);
//			ReportSheetUtils.createRow(row, rownum, detail.getPayrollPeriod().getPeriod().toString(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getIncomeType().getId(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getIncomeType().getDesc(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getRemarks(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getProdHrsAmt(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getEmpId().getFullName(), cellCtr++);
//			ReportSheetUtils.createRow(row, rownum, detail.getCreateDate().toString(), cellCtr++);
//		}
//		return rownum;
//	}
//
//	

	public static void main(String[] args) {
		List<PayrollDetails> details = new ArrayList<PayrollDetails>();
		new ExcelGenerator().generate(details);

	}
}
