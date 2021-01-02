package com.bank.model;

public class Employee extends User{
	
	private String position;
	private String department;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public Employee(String username, String password, String firstName, String lastName, String position, String department) {
		super(username, password, firstName, lastName);
		this.position = position;
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getdepartment() {
		return department;
	}

	public void setdepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [position=" + position + ", department=" + department + ", getId()=" + getId()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + "]";
	}
	
	public String printEmployeeInfo() {
		return "Employee [FirstName=" + getFirstName() + ", LastName=" + getLastName() + ", position=" + position + ", department=" + department + "]";
	}
}
