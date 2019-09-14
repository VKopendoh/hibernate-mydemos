package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Course;
import com.vkopendoh.hibernate.entity.Instructor;
import com.vkopendoh.hibernate.entity.InstructorDetail;
import com.vkopendoh.hibernate.entity.Review;
import com.vkopendoh.hibernate.entity.Student;

public class CreateCourseAndStudents {

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
			
			//create Course
			Course course = new Course("Gaming master class");	
				
			// save the course
			System.out.println("Saving course ");
			session.save(course);
			System.out.println("Course saved" + course);
			
			Student student1 = new Student("Ivan", "Ivanov","ivan@mail.ru");
			Student student2 = new Student("Petr", "Petrov","petr@mail.ru");
			
			course.addStudent(student1);
			course.addStudent(student2);
			
			System.out.println("\nSaving students");
			session.save(student1);
			session.save(student2);
			System.out.println("Students saved: " + course.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			session.close();
			factory.close();
		}

}
}