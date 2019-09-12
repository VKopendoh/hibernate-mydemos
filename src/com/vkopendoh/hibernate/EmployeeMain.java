package com.vkopendoh.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vkopendoh.hibernate.entity.Employee;

public class EmployeeMain {

	public static void main(String[] args) {
		// create session factory
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
						.buildSessionFactory();
				Session session = factory.getCurrentSession();
		Employee emp1 = new Employee("David", "Stain", "Google");
		Employee emp2 = new Employee("Ivan", "Sedov", "Google");
		Employee emp3 = new Employee("Petr", "Ivanov", "Yandex");
		Employee emp4 = new Employee("John", "Doe", "Yandex");
		List<Employee> employees = new ArrayList<Employee>(Arrays.asList(emp1, emp2, emp3, emp4));
		
		try {

			System.out.println("Saving emloyees to db...");
			session.beginTransaction();
			for (Employee emp : employees) {
				session.save(emp);
			}
			session.getTransaction().commit();
			System.out.println("Employees saved!/n/n");
			
			 session = factory.getCurrentSession(); 
			 session.beginTransaction();
			 System.out.println("get emloyee Ivan Sdov from Db by it's pk: " +
			 emp2.getId()); Employee tmpEmp = session.get(Employee.class, emp2.getId());
			 System.out.println("Empoyee retrieved: " + tmpEmp);
			 
			 List<Employee> googlers = session.createQuery("from Employee where company='google'").
					 getResultList(); 
			 
			 System.out.println("Employees from google: ");
			 googlers.forEach(employee->System.out.println(employee));
			 
			 List<Employee> allEmpoyees = session.createQuery("from Employee").getResultList();
			 System.out.println("Delete John Doe by it's id from Db, Before deleting: ");
			 allEmpoyees.forEach(emp->System.out.println(emp));
			 session.createQuery("delete from Employee where id=:tmpid" ).setParameter("tmpid",
					 emp4.getId()).executeUpdate(); session.getTransaction().commit();
			  
			 session = factory.getCurrentSession(); session.beginTransaction();
			 allEmpoyees = session.createQuery("from Employee").getResultList();
			 System.out.println("Delete John Doe by it's id from Db, Before deleting: ");
			 allEmpoyees.forEach(emp->System.out.println(emp));
			 
			 session.getTransaction().commit();
			 
		} finally {
			factory.close();
		}

	}

}
