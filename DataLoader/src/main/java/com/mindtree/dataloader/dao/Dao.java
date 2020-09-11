package com.mindtree.dataloader.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.ResultSetMetaData;

public class Dao {

	public void exportIntoExcel(String database, String tablename)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "?useSSL=false",
				"root", "Statebank1@");
		PreparedStatement st = con.prepareStatement("select * from " + tablename);
		ResultSet rs = st.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colcount = rsmd.getColumnCount();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(tablename);
		XSSFRow rowhead = sheet.createRow((short) 0);
		for (int col = 1; col <= colcount; col++) {
			rowhead.createCell((short) col - 1).setCellValue(rsmd.getColumnName(col));
		}
		int i = 1;
		while (rs.next()) {
			XSSFRow row = sheet.createRow((short) i);
			for (int col = 1; col <= colcount; col++) {
				row.createCell((short) col - 1).setCellValue(rs.getString(col));
			}
			i++;
		}
		String finalfile = "C:/Users/M1044429/Desktop/" + tablename + ".xlsx";
		FileOutputStream fileOut = new FileOutputStream(finalfile);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		System.out.println("Export Complete");

	}

}
