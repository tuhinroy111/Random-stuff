package com.mindtree.hibernateassignment.validations;


import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import com.mindtree.hibernateassignment.entity.College;
import com.mindtree.hibernateassignment.entity.Course;
public class Validation 
{
	public boolean checkName(String name)
	{
		if(name.equals("") ||!Pattern.matches("^[a-zA-Z\\\\s]*$", name))
		{
			System.out.println("Please Enter alphabets");
			return true;
		}
		else
		return false;
	}

	public boolean checkCollege(String collegename) 
	{
		Configuration config=new Configuration().configure().addAnnotatedClass(College.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        String college= (String) session.createSQLQuery("select college_id from college where college_name=?").setParameter(0, collegename).uniqueResult();
        tx.commit();
        session.close();
        if(college!=null)
        	return false;
        else
        return true;
	}

	public boolean checkCourse(String coursename) {
		// TODO Auto-generated method stub
		Configuration config=new Configuration().configure().addAnnotatedClass(Course.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        String course=(String) session.createSQLQuery("select course_id from course where course_name=?").setParameter(0, coursename).uniqueResult();
        tx.commit();
        session.close();
        if(course!=null)
        	return false;
        else
        return true;
	}

	public boolean checkphnNo(String phno) {
		// TODO Auto-generated method stub
		if(!Pattern.matches("^[6-9]\\d{9}$", phno))
		{
			System.out.println("Please Enter valid Phn No");
			return true;
		}
		return false;
	}
}
