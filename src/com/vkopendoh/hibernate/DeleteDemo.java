package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;
import com.vkopendoh.hibernate.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			// get the instructor by id
			int theId = 1;
			Instructor tmpInstructor = session.get(Instructor.class, theId);
			 
			System.out.println("Found instructor: " + tmpInstructor); 
			//delete the insructor
			if(tmpInstructor!=null) {
				System.out.println("Delete " + tmpInstructor);
				session.delete(tmpInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			factory.close();
		}

}
}