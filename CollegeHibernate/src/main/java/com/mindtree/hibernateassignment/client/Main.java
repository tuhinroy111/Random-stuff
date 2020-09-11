package com.mindtree.hibernateassignment.client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mindtree.hibernateassignment.daoImpl.DaoImpl;
import com.mindtree.hibernateassignment.entity.College;
import com.mindtree.hibernateassignment.entity.Course;
import com.mindtree.hibernateassignment.entity.Student;
import com.mindtree.hibernateassignment.validations.Validation;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan=new Scanner(System.in);
		Student student=new Student();
		College college=new College();
		Course course= new Course();
		
		ArrayList<String> colleges=new ArrayList<String>();
		ArrayList<String> courses=new ArrayList<String>();
		ArrayList<Object[]> collegewise=new ArrayList<Object[]>();
		ArrayList<Object[]> studentlist=new ArrayList<Object[]>();
		
		String name=null;
		String collegename=null;
		String coursename=null;
		String phno=null;
		
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
				scan.nextLine();
			}catch(InputMismatchException e)
			{
				scan.next();
			}
			
			switch(choice)
			{
			case 1:
				
				colleges=dao.collegeList();
				for(String college1: colleges )
				{
					System.out.println(college1);
				}
				break;
			case 2:
				courses=dao.courseList();
				for(String course1: courses )
				{
					System.out.println(course1);
				}
				break;
			case 3:
				do {
				System.out.println("Enter Student's Name:");
				name=scan.nextLine();
				}while(validate.checkName(name));
				student.setStudentname(name);
				do {
				System.out.println("Enter College Name from the list:");
				colleges=dao.collegeList();
				for(String college1: colleges )
				{
					System.out.println(college1);
				}
				collegename=scan.nextLine();
				}while(validate.checkCollege(collegename));
				college.setCollegename(collegename);
				do {
				System.out.println("Enter Course Name from the list:");
				courses=dao.courseList();
				for(String course1: courses )
				{
					System.out.println(course1);
				}
				coursename=scan.nextLine();
				}while(validate.checkCourse(coursename));
				course.setCoursename(coursename);
				do {
				System.out.println("Enter Phone Number:");
				phno=scan.nextLine();
				}while(validate.checkphnNo(phno));
				student.setPhno(phno);
				System.out.println("Enter Address:");
				student.setAddress(scan.nextLine());
				dao.saveData(college, course, student);
					System.out.println("Student Added Successfully");
				break;
			case 4:
				do {
				System.out.println("Enter College Name from the list:");
				colleges=dao.collegeList();
				for(String college1: colleges )
				{
					System.out.println(college1);
				}
				clgname=scan.nextLine();
				}while(validate.checkCollege(clgname));
				collegewise=dao.collegewiseStudent(clgname);
				System.out.println("Sl no.\t\tStudent Name\tCollege Name\tCourse Name\tPhn No.\t\t\tAddress");
				System.out.println();
				int collegewisecount=0;
				for(Object[] students: collegewise)
				{
					collegewisecount++;
					System.out.println(collegewisecount+"		"+students[0]+"		"+students[1]+"		"+students[2]+"		"+students[3]+"		"+students[4]);
				}
				break;
			case 5:
				studentlist=dao.studentList();
				System.out.println("Sl no.\t\tStudent Name\tCollege Name\tCourse Name\tPhn No.\t\t\tAddress");
				System.out.println();
				int allcount=0;
				for(Object[] allstudents: studentlist)
				{
					allcount++;
					System.out.println(allcount+"		"+allstudents[0]+"		"+allstudents[1]+"		"+allstudents[2]+"		"+allstudents[3]+"		"+allstudents[4]);
				}
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid Choice");
				break;
			}
		}while(choice!=6);
		
		scan.close();
	}

}
