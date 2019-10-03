package com.cg.frs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.cg.frs.dto.User;

public class ExcelReportView extends AbstractXlsView{
	 
	 @Override
	 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
	 HttpServletResponse response) throws Exception {
	  
	 response.setHeader("Content-Disposition", "attachment;filename=\"user.xls\"");
	 List<User> userList = (List<User>) model.get("userList");
	 Sheet sheet = workbook.createSheet("User Data");
	 Row header = sheet.createRow(0);
	 header.createCell(0).setCellValue("User ID");
	 header.createCell(1).setCellValue("User Name");
	 header.createCell(2).setCellValue("User Mobile");
	 header.createCell(3).setCellValue("User Email");
	  
	 int rowNum = 1;
	 for(User user:userList){
	 Row row = sheet.createRow(rowNum++);
	 row.createCell(0).setCellValue(user.getUserId().longValue());
	 row.createCell(1).setCellValue(user.getUserName());
	 row.createCell(2).setCellValue(user.getUserPhone().longValue());
	 row.createCell(3).setCellValue(user.getEmail());
	 }
	 }
}