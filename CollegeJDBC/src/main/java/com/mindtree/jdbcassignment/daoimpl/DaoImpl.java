package com.mindtree.jdbcassignment.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import com.mindtree.jdbcassignment.dao.StudentDao;
import com.mindtree.jdbcassignment.entity.Student;

public class DaoImpl implements StudentDao {

	public ArrayList<String> collegeList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> colleges = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Not able to load JDBC driver");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			PreparedStatement st = con.prepareStatement("select college_name from college");

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				colleges.add(rs.getString(1));
			}
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}

		return colleges;
	}

	public ArrayList<String> courseList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> courses = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Not able to load JDBC driver");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			PreparedStatement st = con.prepareStatement("select course_name from course");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				courses.add(rs.getString(1));
			}
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}
		return courses;
	}

	public boolean addStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		int collegeid = 0;
		String courseid = null;
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Not able to load JDBC driver");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			PreparedStatement st = con.prepareStatement("select course_id from course where course_name=?");
			st.setString(1, student.getCoursename());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				courseid = rs.getString(1);
			}
			PreparedStatement st1 = con.prepareStatement("select college_id from college where college_name=?");
			st1.setString(1, student.getCollegename());
			ResultSet rs1 = st1.executeQuery();
			while (rs1.next()) {
				collegeid = rs1.getInt(1);
			}
			PreparedStatement st2 = con.prepareStatement(
					"insert into student(student_name, college_id, course_id, phn_no, address) values(?,?,?,?,?)");
			st2.setString(1, student.getStudentname());
			st2.setInt(2, collegeid);
			st2.setString(3, courseid);
			st2.setString(4, student.getPhno());
			st2.setString(5, student.getAddress());
			i = st2.executeUpdate();
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}
		if (i != 0)
			return true;
		else
			return false;
	}

	public ArrayList<String> collegewiseStudent(String college) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> collegewise = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Not able to load JDBC driver");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			PreparedStatement st = con.prepareStatement(
					"select student_name,college_name,course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id =(select college_id from college where college_name=?) and co.college_name=?");
			st.setString(1, college);
			st.setString(2, college);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				collegewise.add(rs.getString(1) + "		" + rs.getString(2) + "		" + rs.getString(3)+ "		" + rs.getString(4)+ "		" + rs.getString(5));
			}
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}
		return collegewise;
	}

	public ArrayList<String> studentList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> studentlist = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Not able to load JDBC driver");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			PreparedStatement st = con.prepareStatement("select student_name,college_name, course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id=co.college_id");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				studentlist.add(rs.getString(1) + "		" + rs.getString(2) + "		" + rs.getString(3)+ "		" + rs.getString(4)+ "		" + rs.getString(5));
			}
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}
		return studentlist;
	}

}
