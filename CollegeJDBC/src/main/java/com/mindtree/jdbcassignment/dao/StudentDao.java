package com.mindtree.jdbcassignment.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mindtree.jdbcassignment.entity.Student;

public interface StudentDao {

	public ArrayList<String> collegeList() throws ClassNotFoundException, SQLException, Exception;
	public ArrayList<String> courseList() throws ClassNotFoundException, SQLException, Exception;
	public boolean addStudent(Student student) throws ClassNotFoundException, SQLException, Exception;
	public ArrayList<String> collegewiseStudent(String college) throws ClassNotFoundException, SQLException, Exception;
	public ArrayList<String> studentList() throws ClassNotFoundException, SQLException, Exception;
}
