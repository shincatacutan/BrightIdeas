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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;

public class ExcelGenerator {
	public String generate(List<PayrollDetails> details) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("OTND");

		int rownum = 0;

		// Sheet Header

		Row header = sheet.createRow(rownum++);
		String headers[] = { "Pay Period", "Income Type", "Description",
				"Remarks", "Value", "Employee", "Create Date" };
		int headerCtr = 0;
		for (String headerName : headers) {
			Cell headerCell = header.createCell(headerCtr++);
			headerCell.setCellValue(headerName);
		}
		
		for(PayrollDetails detail:details){
			int cellCtr=0;
			Row row = sheet.createRow(rownum++);
			createRow(row, rownum, detail.getPayrollPeriod().getPeriod().toString(), cellCtr++);
			createRow(row, rownum, detail.getIncomeType().getId(), cellCtr++);
			createRow(row, rownum, detail.getIncomeType().getDesc(), cellCtr++);
			createRow(row, rownum, detail.getRemarks(), cellCtr++);
			createRow(row, rownum, detail.getProdHrsAmt(), cellCtr++);
			createRow(row, rownum, detail.getEmpId().getFullName(), cellCtr++);
			createRow(row, rownum, detail.getCreateDate().toString(), cellCtr++);
		}

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

	private void createRow(Row row , int rownum, String detail,
			int cellCtr) {
		
		Cell pperiod = row.createCell(cellCtr);
		pperiod.setCellValue(detail);
	}

	public static void main(String[] args) {
		List<PayrollDetails> details = new ArrayList<PayrollDetails>();
		new ExcelGenerator().generate(details);

	}
}
