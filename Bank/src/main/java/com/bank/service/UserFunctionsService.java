package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public interface UserFunctionsService {
	public int registerCustomerAccount(Customer customer) throws BusinessException;
	public Customer loginCustomerAccount(String username, String password) throws BusinessException;
	public Employee loginEmployeeAccount(String username, String password) throws BusinessException;
}
