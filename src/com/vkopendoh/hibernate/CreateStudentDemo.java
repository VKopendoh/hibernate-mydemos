package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {
			//create a Student obj
			System.out.println("Create new student object ...");
			Student myStudent = new Student("Den", "Feldman", "den@mail.ru");				
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			System.out.println("Saving student object to DB....");
			session.save(myStudent);
						
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			factory.close();
		}

}
}