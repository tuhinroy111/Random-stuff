package com.mindtree.jdbcassignment.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mindtree.jdbcassignment.daoimpl.DaoImpl;
import com.mindtree.jdbcassignment.entity.Student;
import com.mindtree.jdbcassignment.validations.Validation;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		
		
		
		String name=null;
		String collegename=null;
		String coursename=null;
		String phno=null;
		Student stu=new Student();
		DaoImpl dao=new DaoImpl();
		Validation validate=new Validation();
		String clgname=null;
		int choice=0;
		do
		{
			System.out.println("Enter Your Choice");
			System.out.println("1. Display College List");
			System.out.println("2. Display Course List");
			System.out.println("3. Add Student");
			System.out.println("4. Display Student By College Name");
			System.out.println("5. Display All Students");
			System.out.println("6. Exit");
			try{
				choice=scan.nextInt();
			}
			catch(InputMismatchException e)
			{
				scan.next();
			}
			//scan.nextLine();
			switch(choice)
			{
			case 1:
				ArrayList<String> colleges=new ArrayList<String>();
				try {
					colleges=dao.collegeList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				for(String college: colleges )
				{
					System.out.println(college);
				}
				break;
			case 2:
				ArrayList<String> courses=new ArrayList<String>();
				try {
					courses=dao.courseList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				for(String course: courses )
				{
					System.out.println(course);
				}
				break;
			case 3:
				ArrayList<String> colleges1=new ArrayList<String>();
				ArrayList<String> courses1=new ArrayList<String>();
				do {
				System.out.println("Enter Student's Name:");
				name=scan.nextLine();
				}while(validate.checkName(name));
				stu.setStudentname(name);
				do {
				System.out.println("Enter College Name from the list:");
				try {
					colleges1=dao.collegeList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				for(String college: colleges1 )
				{
					System.out.println(college);
				}
				collegename=scan.nextLine();
				}while(validate.checkCollege(collegename));
				stu.setCollegename(collegename);
				do {
				System.out.println("Enter Course Name from the list:");
				try {
					courses1=dao.courseList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				for(String course: courses1 )
				{
					System.out.println(course);
				}
				coursename=scan.nextLine();
				}while(validate.checkCourse(coursename));
				stu.setCoursename(coursename);
				do {
				System.out.println("Enter Phone Number:");
				phno=scan.nextLine();
				}while(validate.checkphnNo(phno));
				stu.setPhno(phno);
				System.out.println("Enter Address:");
				stu.setAddress(scan.nextLine());
				try {
					if(dao.addStudent(stu))
						System.out.println("Student Added Successfully");
					else
						System.out.println("Not able to add student");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				ArrayList<String> collegewise=new ArrayList<String>();
				ArrayList<String> colleges2=new ArrayList<String>();
				do {
				System.out.println("Enter College Name from the list:");
				try {
					colleges2=dao.collegeList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				for(String college: colleges2 )
				{
					System.out.println(college);
				}
				clgname=scan.nextLine();
				}while(validate.checkCollege(clgname));
				try {
					collegewise=dao.collegewiseStudent(clgname);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				System.out.println("Sl no.\t\tStudent Name\tCollege Name\tCourse Name\tPhn No.\t\t\tAddress");
				System.out.println();
				int collegewisecount=0;
				for(String students: collegewise)
				{
					collegewisecount++;
					System.out.println(collegewisecount+"		"+students);
				}
				break;
			case 5:
				ArrayList<String> studentlist=new ArrayList<String>();
				try {
					studentlist=dao.studentList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				System.out.println("Sl no.\t\tStudent Name\tCollege Name\tCourse Name\tPhn No.\t\t\tAddress");
				System.out.println();
				int allcount=0;
				for(String allstudents: studentlist)
				{
					allcount++;
					System.out.println(allcount+"		"+allstudents);
				}
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid choice from the List");
				break;
			}
		}while(choice!=6);
		
		scan.close();
	}

}

