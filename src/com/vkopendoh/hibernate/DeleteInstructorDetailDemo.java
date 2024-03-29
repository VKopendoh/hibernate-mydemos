package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			//get the instructor detail object
			int theId = 3;
			InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, theId);			
			
			System.out.println("tmpInstructorDetail: " + tmpInstructorDetail);
			//print associated instructor
			System.out.println("the Instructor: "+ tmpInstructorDetail.getInstructor());
			
			//delete instructor detail
			session.delete(tmpInstructorDetail);		
			
			//remove the associated object reference break bi-directional link
			tmpInstructorDetail.getInstructor().setInstructorDetail(null);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//handle connection leak issue
			session.close();
			
			factory.close();
		}

}
}