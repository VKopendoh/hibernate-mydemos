package com.vkopendoh.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {			
			//start transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			dispayStudents(theStudents);
			System.out.println("\n\n\n");
			//query students:
			theStudents = session.createQuery("from Student s where s.lastName='Fain'").getResultList();
			dispayStudents(theStudents);
			
			System.out.println("\n\n\n");
			theStudents = session.createQuery("from Student s where s.lastName='Fain' OR s.firstName='Freddy'").getResultList();
			dispayStudents(theStudents);
			
			System.out.println("\n\n\n");
			theStudents = session.createQuery("from Student s where s.email LIKE '%mail.ru'").getResultList();
			dispayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			factory.close();
		}

}

	private static void dispayStudents(List<Student> theStudents) {
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}
}