package com.mindtree.hibernateassignment.entity;

import javax.persistence.*;


@Entity
public class Student {
	
	@Column(name="Student_Name")
	private String studentname;
	
	
	@ManyToOne
	@JoinColumn(name="College_Id")
	private College collegeid;
	@ManyToOne
	@JoinColumn(name="Course_Id")
	private Course courseid;
	@Column(name="Address")
	private String address;
	@Column(name="Phn_No")
	private String phno;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Student_Id")
	private int studentid;
	
	public Student()
	{
		
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public College getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(College collegeid) {
		this.collegeid = collegeid;
	}

	public Course getCourseid() {
		return courseid;
	}

	public void setCourseid(Course courseid) {
		this.courseid = courseid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	} 
	

}
