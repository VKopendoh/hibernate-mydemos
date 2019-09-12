package com.vkopendoh.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Student;

public class ReadStudentDemo {

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
			Student myStudent = new Student("Freddy", "Krugger", "fred@mail.ru");				
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			System.out.println("Saving student object to DB....");
			System.out.println(myStudent);
			session.save(myStudent);
			//commit transaction
			session.getTransaction().commit();		
			
			//find student's id:pk
			System.out.println("Saved student generated id: " + myStudent.getId());
			 
			//get new session and start new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();			
			
			//retriev student from Db
			Student newStudent = session.get(Student.class, myStudent.getId());
			
			session.getTransaction().commit();
			
			System.out.println("get newStudent from Db: " + newStudent);
			
			System.out.println("Done!");	
			
				
		}finally {
			factory.close();
		}

}
}