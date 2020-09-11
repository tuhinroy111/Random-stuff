package com.mindtree.swingapp.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validation {
	public boolean checkName(String name) {
		if (name.equals("") || !Pattern.matches("^[a-zA-Z\\\\s]*$", name)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollege(String collegeName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
				"Statebank1@");
		PreparedStatement st = con.prepareStatement("select * from college where college_name=?");
		st.setString(1, collegeName);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			return false;
		}
		con.close();
		System.out.println("Please select a college from the list provided");
		return true;

	}

	public boolean checkCourse(String coursename) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
				"Statebank1@");
		PreparedStatement st = con.prepareStatement("select * from course where course_name=?");
		st.setString(1, coursename);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			return false;
		}
		con.close();
		System.out.println("Please select a course from the list provided");
		return true;

	}

	public boolean checkphnNo(String phno) {
		// TODO Auto-generated method stub
		if (!Pattern.matches("^[6-9]\\d{9}$", phno)) {
			return true;
		}
		return false;
	}
}
