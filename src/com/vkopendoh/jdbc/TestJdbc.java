package com.vkopendoh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useLegacyDatetimeCode=false&amp;serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		System.out.println("DDDDDDD");
		try {
		Connection conn =  DriverManager.getConnection(jdbcUrl,user,pass);
		
		System.out.println("Succeful connection!");
	}catch(Exception e) {
		e.printStackTrace();
	}

}}