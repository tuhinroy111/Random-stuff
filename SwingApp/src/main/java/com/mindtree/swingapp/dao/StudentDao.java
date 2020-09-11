package com.mindtree.swingapp.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mindtree.swingapp.entity.Student;
import com.mindtree.swingapp.entity.Studentdto;

public interface StudentDao {

	public ArrayList<String> collegeList() throws ClassNotFoundException, SQLException, Exception;

	public ArrayList<String> courseList() throws ClassNotFoundException, SQLException, Exception;

	public boolean addStudent(Student student) throws ClassNotFoundException, SQLException, Exception;

	public ArrayList<Studentdto> collegewiseStudent(String college)
			throws ClassNotFoundException, SQLException, Exception;

	public ArrayList<Studentdto> studentList() throws ClassNotFoundException, SQLException, Exception;

	public ArrayList<Studentdto> coursewiseStudent(String course) throws Exception;
}
