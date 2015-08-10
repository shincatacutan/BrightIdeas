package com.uhg.ssmo.otnd.excel.decorator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

public class ReportSheetUtils {
	public static int createHeader(HSSFWorkbook workbook,Row header, String[] headers, int headerCtr) {
		
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		HSSFFont headerFont= workbook.createFont();
		headerFont.setFontName(HSSFFont.FONT_ARIAL);
		headerFont.setFontHeightInPoints((short)9);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerStyle.setFont(headerFont);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		
		for (String headerName : headers) {
			Cell headerCell = header.createCell(headerCtr++);
			headerCell.setCellValue(headerName);
			
		}
		
		for(int i = 0; i< headers.length; i++){
			header.getCell(i).setCellStyle(headerStyle);
		}
		return headerCtr;
	}
	public static void createRow(HSSFWorkbook workbook,Row row , int rownum, String detail,
			int cellCtr) {
		
		Cell pperiod = row.createCell(cellCtr);
		pperiod.setCellValue(detail);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFFont bodyFont= workbook.createFont();
		bodyFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		style.setFont(bodyFont);
		row.getCell(cellCtr).setCellStyle(style);
	}
	
	public static void autoSizeWidth(HSSFSheet otnd, String[] headers) {
		for(int i = 0; i<headers.length; i++){
			otnd.autoSizeColumn(i);
		}
	}
	
}
