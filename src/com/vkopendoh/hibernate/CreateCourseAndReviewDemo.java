package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Course;
import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;
import com.vkopendoh.hibernate.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {				
			//start transaction
			session.beginTransaction();
			
			//create Course
			Course course = new Course("Gaming master class");
			
			//add some reviews
			course.add(new Review("Cool course"));
			course.add(new Review("Boooring!!!"));
			course.add(new Review("Great course go for it!"));
			
			// save the course
			System.out.println("Saving course "+ course);
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			session.close();
			factory.close();
		}

}
}