package com.bank.main;

import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import com.bank.account.login.CustomerAccountLogin;
import com.bank.account.login.EmployeeAccountLogin;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.UserFunctionsService;
import com.bank.service.impl.UserFunctionsServiceImpl;

public class BankingMain {

	private static Logger log=Logger.getLogger(BankingMain.class);
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		log.info("Welcome to Hieu's Bank APP V1.0.0");
		log.info("--------------------------------------------------");
		UserFunctionsService userFunctionsService = new UserFunctionsServiceImpl();
		int ch = 0;
		String s1 = "hi";
	
		do {
			log.info("User Menu, Please select one option below");
			log.info("=========================================");
			log.info("1)Login to account");
			log.info("2)Register a new Customer account");
			log.info("3)EXIT Menu");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn(e.getMessage() + " is Invalid");
				continue;
			}
			switch (ch) {
			case 1:
				int ch1=0;
				do {					
					log.info("\nWelcome to Login Account Menu, Please select one option below");
					log.info("---------------------------");
					log.info("1)I am a customer");
					log.info("2)I am a employee");
					log.info("3)Go Back");
					try {
						ch1= Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage() + " is Invalid");
						continue;
					}
					switch (ch1) {
					case 1:
						log.info("Enter customer username");
						String username = scanner.nextLine().trim();
						log.info("Enter customer password");
						String password = scanner.nextLine().trim();
						try {
							Customer customer = userFunctionsService.loginCustomerAccount(username, password);
							if (customer!= null) {
								log.info("Login to customer account sucessful");
								CustomerAccountLogin.activateAccount(customer);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						break;
					case 2:
						log.info("Enter employee username");
						String username2 = scanner.nextLine().trim();
						log.info("Enter employee password");
						String password2 = scanner.nextLine().trim();
						try {
							Employee employee = userFunctionsService.loginEmployeeAccount(username2, password2);
							if (employee!= null) {
								log.info("Login to employee account...");
								EmployeeAccountLogin.activateAccount(employee);;
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						break;
					case 3:
						log.info("Back to User Menu...");
						break;
					default:
						log.error("Invalid Choice!!!! Please enter choice between 1-3 only");
						break;
					}
				} while(ch1!=3);	
				break;
			case 2:
				log.info("\nRegister a new Customer account. Please fill all the required fields");
				log.info("---------------------------");
				log.info("Please choose a username");
				String username = scanner.nextLine().trim();
				log.info("Please choose a password which is satisfied:");
				log.info("1)Password is at least 6 character and no longer than 20 characters");
				log.info("2)Password has at least one uppercase and one lowercase letters");
				log.info("3)Password has at least one number");
				String password = scanner.nextLine().trim();
				log.info("Enter your first name");
				String firstName = scanner.nextLine().trim();
				log.info("Enter your last name");
				String lastName = scanner.nextLine().trim();
				log.info("\nThese are the Optional fields");
				log.info("---------------------------");
				log.info("Enter your email , keep blank if you don't want");
				String email = scanner.nextLine().trim();
				log.info("Enter your 10 digits phone number, keep blank if you don't want");
				String phone = scanner.nextLine().trim();
				Customer customer = new Customer(username, password, firstName, lastName, email, phone);
				do {
					log.info("Review your application. Type Y/y to confirm or type N/n for cancel");
					log.info("----------------------------------------------------------------");
					log.info(customer.toString());
					String s = scanner.nextLine().trim();
					if(s.equalsIgnoreCase("y")) {
						try {
							int c = userFunctionsService.registerCustomerAccount(customer);
							if (c > 0) {
								log.info("Your customer account have been registered successfully");
								log.info(customer.printCustomerInfo());
							} 
							break;
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
					} else if (s.equalsIgnoreCase("n")) {
						log.info("Cancel for register a new customer account...");
						break;
					} else {
						log.error("Invalid input value. Please try again...");
						continue;
					}
					break;
				} while(true);
				break;
			case 3:
				log.info("Thank you for using Bank App.");
				break;
			default:
				log.error("Invalid Choice!!!! Please enter choice between 1-3 only");
				break;
			}		
		} while(ch != 3);
		
		
		
	}

}
