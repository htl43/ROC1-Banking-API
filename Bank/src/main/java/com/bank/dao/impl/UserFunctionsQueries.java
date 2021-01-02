package com.bank.dao.impl;

public class UserFunctionsQueries {
	
	public static final String INSERT_CUSTOMER_ACCOUNT = "INSERT INTO project.customer_account (username,\"password\",first_name,last_name,email,phone) "
			+ "VALUES(?,?,?,?,?,?)"; 
	
	public static final String LOGIN_CUSTOMER_ACCOUNT = "SELECT id, username, password, first_name, last_name, email,phone "
			+ "FROM project.customer_account WHERE username=? AND password=?";
	
	public static final String LOGIN_EMPLOYEE_ACCOUNT = "SELECT id, username, password, first_name, last_name, position,department "
			+ "FROM project.employee_account WHERE username=? AND password=?";
	
}
