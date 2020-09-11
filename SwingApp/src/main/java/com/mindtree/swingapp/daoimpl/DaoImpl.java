package com.mindtree.swingapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import com.mindtree.swingapp.dao.StudentDao;
import com.mindtree.swingapp.entity.Student;
import com.mindtree.swingapp.entity.Studentdto;
import com.mindtree.swingapp.utilities.DBConnector;

public class DaoImpl implements StudentDao {

	public ArrayList<String> collegeList() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		con = DBConnector.dbConnection();
		ArrayList<String> colleges = new ArrayList<String>();
		try {
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
		Connection con = null;
		con = DBConnector.dbConnection();
		try {
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
		Connection con = null;
		con = DBConnector.dbConnection();
		try {
			System.out.println(student.getCoursename());
			PreparedStatement st = con.prepareStatement("select course_id from course where course_name=?");
			st.setString(1, student.getCoursename());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				courseid = rs.getString(1);
			}
			System.out.println(student.getCollegename());
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

	public ArrayList<Studentdto> collegewiseStudent(String college) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		con = DBConnector.dbConnection();
		ArrayList<Studentdto> collegewise = new ArrayList<Studentdto>();
		try {
			PreparedStatement st = con.prepareStatement(
					"select student_name,college_name,course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id =(select college_id from college where college_name=?) and co.college_name=?");
			st.setString(1, college);
			st.setString(2, college);
			ResultSet rs = st.executeQuery();
			int i = 1;

			while (rs.next()) {
				Studentdto studentdto = new Studentdto();
				studentdto.setSlno(i++);
				studentdto.setStudentname(rs.getString(1));
				studentdto.setCollegename(rs.getString(2));
				studentdto.setCoursename(rs.getString(3));
				studentdto.setAddress(rs.getString(4));
				studentdto.setPhno(rs.getString(5));
				collegewise.add(studentdto);
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

	public ArrayList<Studentdto> studentList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Studentdto> studentlist = new ArrayList<Studentdto>();
		Connection con = null;
		con = DBConnector.dbConnection();
		try {
			PreparedStatement st = con.prepareStatement(
					"select student_name,college_name, course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id=co.college_id");
			ResultSet rs = st.executeQuery();
			int i = 1;

			while (rs.next()) {
				Studentdto studentdto = new Studentdto();
				studentdto.setSlno(i++);
				studentdto.setStudentname(rs.getString(1));
				studentdto.setCollegename(rs.getString(2));
				studentdto.setCoursename(rs.getString(3));
				studentdto.setAddress(rs.getString(4));
				studentdto.setPhno(rs.getString(5));
				studentlist.add(studentdto);
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

	public ArrayList<Studentdto> coursewiseStudent(String course) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		con = DBConnector.dbConnection();
		ArrayList<Studentdto> coursewise = new ArrayList<Studentdto>();
		try {
			PreparedStatement st = con.prepareStatement(
					"select student_name,college_name,course_name,phn_no,address from student s ,college co,course cr where s.college_id=co.college_id and s.course_id =(select course_id from course where course_name=?) and cr.course_name=?");
			st.setString(1, course);
			st.setString(2, course);
			ResultSet rs = st.executeQuery();
			int i = 1;

			while (rs.next()) {
				Studentdto studentdto = new Studentdto();
				studentdto.setSlno(i++);
				studentdto.setStudentname(rs.getString(1));
				studentdto.setCollegename(rs.getString(2));
				studentdto.setCoursename(rs.getString(3));
				studentdto.setAddress(rs.getString(4));
				studentdto.setPhno(rs.getString(5));
				coursewise.add(studentdto);
			}
			con.close();
		} catch (SQLSyntaxErrorException e) {
			throw new SQLSyntaxErrorException("Error in SQL syntax");
		} catch (SQLException e) {
			throw new SQLException("Connection with database failed");
		} catch (Exception e) {
			throw new Exception("Some error occured");
		}

		return coursewise;
	}

}
