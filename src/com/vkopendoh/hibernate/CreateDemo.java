package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;
import com.vkopendoh.hibernate.entity.Student;

public class CreateDemo {

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
			//create the objects
			Instructor theInstructor = new Instructor("Chad", "Darby", "some@mail.ru");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube/ch", "Coding...");
			
			//associate the objects
			theInstructor.setInstructorDetail(tempInstructorDetail);		
			
			//start transaction
			session.beginTransaction();
			
			//save the instructor (this will also save instr detail! because cascade)
			System.out.println("saving "+ theInstructor);
			session.save(theInstructor);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			factory.close();
		}

}
}