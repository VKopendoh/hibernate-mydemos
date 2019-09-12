package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
			
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student objects
			System.out.println("Create 3 new student objects ...");
			Student myStudent1 = new Student("Poul", "Fain", "poul@mail.ru");
			Student myStudent2 = new Student("Boris", "Surname", "boris@mail.ru");	
			Student myStudent3 = new Student("Ariel", "Feldman", "ariel@mail.ru");	
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			System.out.println("Saving student object to DB....");
			session.save(myStudent1);
			session.save(myStudent2);
			session.save(myStudent3);
						
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");		
		}finally {
			factory.close();
		}


	}

}
