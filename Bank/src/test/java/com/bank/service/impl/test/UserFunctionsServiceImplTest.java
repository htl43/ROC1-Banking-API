package com.bank.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.UserFunctionsService;
import com.bank.service.impl.UserFunctionsServiceImpl;

class UserFunctionsServiceImplTest {
	
	private static UserFunctionsService userService;
	
	@BeforeAll
	public static void createUserService() throws BusinessException {
		userService = new UserFunctionsServiceImpl();
	}
	
	@Test
	void testValidRegisterCustomerAccount() throws BusinessException {
		Random rand = new Random();
		String username="";
		for(int i=0; i<6; i++) {
			int randNumber = rand.nextInt(9);
			username += randNumber;
		}
		Customer validCustomer = new Customer();
		validCustomer.setUsername(username);
		validCustomer.setPassword("Abc123");
		validCustomer.setFirstName("tester");
		validCustomer.setLastName("test");
		int rs = userService.registerCustomerAccount(validCustomer);
		assertEquals(true, rs>0);
		
	}
	
	@Test
	void testInvalidRegisterCustomerAccount()  {	
		String username="";
		Customer invalidCustomer = new Customer();
		invalidCustomer.setUsername(username);
		invalidCustomer.setPassword("Abc123");
		invalidCustomer.setFirstName("tester");
		invalidCustomer.setLastName("test");	
		try {
			int rs = userService.registerCustomerAccount(invalidCustomer);
			assertEquals(true, rs<=0);
		} catch (BusinessException e) {
			assertNotNull(e);
		}
	}

	@Test
	void testValidLoginCustomerAccount() throws BusinessException {
		Customer validLoginCustomer = userService.loginCustomerAccount("customer1", "Customer1");
		assertNotNull(validLoginCustomer);
		assertEquals("customer1",validLoginCustomer.getUsername());
		assertEquals("Customer1",validLoginCustomer.getPassword());
		assertEquals("Peter",validLoginCustomer.getFirstName());
		assertEquals("Grunder",validLoginCustomer.getLastName());
		
	}

	@Test
	void testInvalidLoginEmployeeAccount() {
		try {
			Employee loginEmployee=null;
			loginEmployee = userService.loginEmployeeAccount("wrong username", "wrong password");
			assertNull(loginEmployee);
			
		} catch (BusinessException e) {
			assertNotNull(e);
			System.out.println(e.getMessage());
		}
	}

}
