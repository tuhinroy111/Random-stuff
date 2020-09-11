package com.mindtree.hibernateassignment.dao;

import com.mindtree.hibernateassignment.entity.College;
import com.mindtree.hibernateassignment.entity.Course;
import com.mindtree.hibernateassignment.entity.Student;

public interface Dao 
{
	public void saveData(College c,Course co, Student s);
}
