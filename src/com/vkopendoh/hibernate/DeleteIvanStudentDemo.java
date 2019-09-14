package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Course;
import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;
import com.vkopendoh.hibernate.entity.Review;
import com.vkopendoh.hibernate.entity.Student;

public class DeleteIvanStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {				
			//start transaction
			session.beginTransaction();
			
			int id =1;
			Student student = session.get(Student.class, id);
		    System.out.println("Course get" + student.getCourses());
			
		    System.out.println("\nDelete student " + student);
		    session.delete(student);
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			session.close();
			factory.close();
		}

}
}