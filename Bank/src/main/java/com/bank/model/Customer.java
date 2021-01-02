package com.bank.model;

public class Customer extends User {
	
	private String email;
	private String phone;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public Customer(String username, String password, String firstName, String lastName) {
		
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String username, String password, String firstName, String lastName, String email, String phone) {
		super(username, password, firstName, lastName);
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [email=" + email + ", phone=" + phone + ", getId()=" + getId() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getFirstName()=" + getFirstName() + 
				", getLastName()=" + getLastName() + "]";
	}

	
	public String printCustomerInfo() {
		return "Customer [ FirstName=" + getFirstName() + ", LastName=" + getLastName() + ", email=" + email + ", phone=" + phone + "]";
	}
}
