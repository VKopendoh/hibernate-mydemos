package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Course;
import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {				
			//start transaction
			session.beginTransaction();
			
			//get instructor from db
			int id= 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			//create courses
			Course course1 = new Course("Java ultimate courses");
			Course course2 = new Course("C# super-dupper courses");
			
			//add courses to instructor
			instructor.add(course1);
			instructor.add(course2);
			
			//save them
			session.save(course1);
			session.save(course2);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			session.close();
			factory.close();
		}

}
}