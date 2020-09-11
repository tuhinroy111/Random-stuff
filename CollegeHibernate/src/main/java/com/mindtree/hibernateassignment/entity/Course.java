package com.mindtree.hibernateassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course 
{
	@Id
	@Column(name="Course_Id")
	private String courseid;
	@Column(name="Course_Name")
	private String coursename;
	
	public Course()
	{
		
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
}
