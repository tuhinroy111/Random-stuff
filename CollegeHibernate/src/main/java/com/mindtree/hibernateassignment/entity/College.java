package com.mindtree.hibernateassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class College 
{
	@Id
	@Column(name="College_Id")
	private String collegeid;
	@Column(name="College_Name")
	private String collegename;
	
	public College()
	{
		
	}

	public String getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	
	
}
