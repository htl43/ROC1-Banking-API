package com.bank.service.impl;

import org.apache.log4j.Logger;

import com.bank.dao.UserFunctionsDAO;
import com.bank.dao.impl.UserFunctionsDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.UserFunctionsService;

public class UserFunctionsServiceImpl implements UserFunctionsService{
	
	private static Logger log=Logger.getLogger(UserFunctionsServiceImpl.class);
	
	private UserFunctionsDAO userFunctionDAO = new UserFunctionsDAOImpl();

	@Override
	public int registerCustomerAccount(Customer customer) throws BusinessException {
		int c=0;
		String password  = customer.getPassword();
		if(customer.getUsername().isEmpty() || customer.getUsername()==null) {
			throw new BusinessException("Username can't be blank");
		} 
		if(customer.getPassword().isEmpty() || customer.getPassword()==null) {
			throw new BusinessException("Password can't be blank");
		} 
		if(customer.getFirstName().isEmpty() || customer.getFirstName()==null){
			throw new BusinessException("Firstname can't be blank");
		} 
		if(customer.getLastName().isEmpty() || customer.getLastName()==null) {
			throw new BusinessException("Lastname can't be blank");
		}
		if(password.length()<6 || password.length()>20) {
			throw new BusinessException("Password must not shorter than 6 letters and no longer than 20 character");
		} else if(password.matches("[^A-Z]+")) {
			throw new BusinessException("Password must have at least one Uppercase Letter");
		} else if (password.matches("[^a-z]+")) {
			throw new BusinessException("Password must have at least one Lowercase Letter");
		} else if (password.matches("[^1-9]+")) {
			throw new BusinessException("Password must have at least one Number");
		} else if(customer.getEmail()!=null && !customer.getEmail().isEmpty() && !customer.getEmail().matches("[a-zA-Z0-9]+[@]{1}[a-zA-Z]+[.]{1}[a-zA-Z]+.*$")) {
			throw new BusinessException("Entered email " + customer.getEmail() + " is invalid");	
		} else if(customer.getPhone()!=null && !customer.getPhone().isEmpty() && !customer.getPhone().matches("[0-9]{10}")) {
			throw new BusinessException("Entered phone " + customer.getPhone() + " is invalid");	
		} 
		else {
			c = userFunctionDAO.registerCustomerAccount(customer);
		}				
		return c;
	}

	@Override
	public Customer loginCustomerAccount(String username, String password) throws BusinessException {
		Customer customer=null;
		if(username.isEmpty() || username==null) {
			throw new BusinessException("Error. The username can't be empty");
		}
		if(password.isEmpty() || password==null) {
			throw new BusinessException("Error. The password can't be empty");
		}		
		customer = userFunctionDAO.loginCustomerAccount(username, password);
		
		return customer;
	}

	@Override
	public Employee loginEmployeeAccount(String username, String password) throws BusinessException {
		Employee employee=null;
		if(username.isEmpty() || username==null) {
			throw new BusinessException("Error. The username can't be empty");
		}
		if(password.isEmpty() || password==null) {
			throw new BusinessException("Error. The password can't be empty");
		}	
		employee = userFunctionDAO.loginEmployeeAccount(username, password);
		return employee;
	}
	

}
