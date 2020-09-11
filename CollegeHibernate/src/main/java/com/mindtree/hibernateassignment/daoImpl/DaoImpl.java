package com.mindtree.hibernateassignment.daoImpl;


import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mindtree.hibernateassignment.dao.Dao;
import com.mindtree.hibernateassignment.entity.College;
import com.mindtree.hibernateassignment.entity.Course;
import com.mindtree.hibernateassignment.entity.Student;


public class DaoImpl implements Dao
{

	@SuppressWarnings("unchecked")
	public ArrayList<String> collegeList()
	{
		ArrayList<String> colleges=new ArrayList<String>();
		Configuration config=new Configuration().configure().addAnnotatedClass(College.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        colleges=(ArrayList<String>) session.createSQLQuery("select college_name from College").list();
        tx.commit();
        session.close();
        return colleges;
		
	}
	
	public ArrayList<String> courseList() {
		// TODO Auto-generated method stub
		ArrayList<String> courses=new ArrayList<String>();
		Configuration config=new Configuration().configure().addAnnotatedClass(Course.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        courses=(ArrayList<String>) session.createSQLQuery("select course_name from Course").list();
        tx.commit();
        session.close();
        return courses;
	}
	
	public void saveData(College c,Course co, Student s) {
		
		Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(College.class).addAnnotatedClass(Course.class);
        ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        String cid=(String) session.createSQLQuery("select college_id from College where college_name=?").setParameter(0, c.getCollegename()).uniqueResult();
        c.setCollegeid(cid);
        s.setCollegeid(c);
        String coid=(String) session.createSQLQuery("select course_id from Course where course_name=?").setParameter(0, co.getCoursename()).uniqueResult();
        co.setCourseid(coid);
        s.setCourseid(co);
        session.save(s);
        tx.commit();
        session.close();
	}
	
	
	public ArrayList<Object[]> collegewiseStudent(String clgname) {
		// TODO Auto-generated method stub
		ArrayList<Object[]> students=new ArrayList<Object[]>();
		Configuration config=new Configuration().configure().addAnnotatedClass(Course.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        students=(ArrayList<Object[]>) session.createSQLQuery("select student_name,college_name,course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id =(select college_id from college where college_name=?) and co.college_name=?").setParameter(0, clgname).setParameter(1,clgname).list();
        tx.commit();
        session.close();
        return students;
	}
	
	public ArrayList<Object[]> studentList() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> students=new ArrayList<Object[]>();
		Configuration config=new Configuration().configure().addAnnotatedClass(Course.class);
		ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf=config.buildSessionFactory(reg);
        Session session= sf.openSession();
        Transaction tx=session.beginTransaction();
        students=(ArrayList<Object[]>) session.createSQLQuery(" select student_name,college_name, course_name,phn_no,address from student s ,college co,course cr where s.course_id=cr.course_id and s.college_id=co.college_id").list();
        tx.commit();
        session.close();
        return students;
	}
	
}
