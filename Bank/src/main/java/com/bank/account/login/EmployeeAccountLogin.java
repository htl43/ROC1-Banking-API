package com.bank.account.login;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.service.EmployeeFunctionsService;
import com.bank.service.impl.EmployeeFunctionsServiceImpl;

public class EmployeeAccountLogin {
private static Logger log=Logger.getLogger(CustomerAccountLogin.class);
	
	public static void activateAccount(Employee employee) {	
		EmployeeFunctionsService employeeFunctionsService = new EmployeeFunctionsServiceImpl();
		Scanner scanner = new Scanner(System.in);
		int ch = 0;
		log.info(employee.printEmployeeInfo());
		log.info("--------------------------------------------------");	
		do {
			log.info("\nEmployee Account Menu, Please select one option below");
			log.info("=========================================");
			log.info("1)View All Bank Accounts");
			log.info("2)View Applying Bank Accounts");
			log.info("3)View Approved Bank Accounts");
			log.info("4)Approve or Reject an Bank Account");
			log.info("5)View Customer Account Details By Bank ID");
			log.info("6)View a log of all transactions");
			log.info("7)Logout");
			try {
				ch = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				log.warn(e.getMessage() + " is Invalid");
				continue;
			}
			switch (ch) {
			case 1:
				log.info("\nLoading all customer bank accounts...");
				try {
					List<BankAccount> customerBankAccounts=employeeFunctionsService.viewAllBankAccounts();
					if(customerBankAccounts!=null && customerBankAccounts.size()>0) {
						for(BankAccount b:customerBankAccounts) {
							log.info(b.toString());
						}
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 2:
				log.info("\nLoading customer applying bank accounts...");
				try {
					List<BankAccount> customerBankAccounts=employeeFunctionsService.viewApplyBankAccounts();
					if(customerBankAccounts!=null && customerBankAccounts.size()>0) {
						Set<Customer> customerList = new HashSet<>();
						for(BankAccount b:customerBankAccounts) {
							Customer customer = employeeFunctionsService.getCustomerAccountByCustomerID(b.getCustomerId());
							customerList.add(customer);

						}
						for(Customer c: customerList) {
							log.info("=======================================");
							log.info(c.printCustomerInfo());
							log.info("---------------------------------------");
							customerBankAccounts = employeeFunctionsService.getBanksByCustomerID(c.getId());
							for(BankAccount b:customerBankAccounts) {
								if(b.getStatus().equalsIgnoreCase("spending")) {
									log.info(b.toString());
								}				
							}
						}
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}			
				break;
			case 3:
				log.info("Loading customer activate bank accounts...");
				try {
					List<BankAccount> customerBankAccounts=employeeFunctionsService.viewActiveBankAccounts();
					if(customerBankAccounts!=null && customerBankAccounts.size()>0) {
						Set<Customer> customerList = new HashSet<>();
						for(BankAccount b:customerBankAccounts) {
							Customer customer = employeeFunctionsService.getCustomerAccountByCustomerID(b.getCustomerId());
							customerList.add(customer);

						}
						for(Customer c: customerList) {
							log.info("=======================================");
							log.info(c.printCustomerInfo());
							log.info("---------------------------------------");
							customerBankAccounts = employeeFunctionsService.getBanksByCustomerID(c.getId());
							for(BankAccount b:customerBankAccounts) {
								if(b.getStatus().equalsIgnoreCase("approve")) {
									log.info(b.toString());
								}
							}
						}
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 4:
				int id;
				String status = null;
				int ch1=0;
				log.info("Enter the bank Id");
				try {
					id = Integer.parseInt(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					log.warn(e.getMessage() + " is Invalid");
					continue;
				}
				do {
					log.info("Select one option below");
					log.info("1)Approve this bank");
					log.info("2)Reject this bank");
					try {
						ch1 = Integer.parseInt(scanner.nextLine().trim());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage() + " is Invalid");
						continue;
					}
					if(ch1==1) {
						status = "approve";
					} else if(ch1==2) {
						status = "reject";
					} else {
						log.error("Invalid Choice!!!! Please enter choice between 1 or 2 only");
						ch1 = 0;
					}
				}while(ch1==0);
				
				try {
					if(employeeFunctionsService.updateBankAccountStatusById(id, status) > 0) {
						log.info("Update status " + status + " for the bank account with id= " + id + " successful");
					} else {
						log.error("System can't find any record that match with bank id " + id);
					}			
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 5:
				int bankId;
				log.info("Enter bank Id");
				try {
					bankId = Integer.parseInt(scanner.nextLine().trim());
				} catch (NumberFormatException e) {
					log.warn(e.getMessage() + " is Invalid");
					continue;
				}
				try {
					BankAccount customerBank = employeeFunctionsService.getBankAccountById(bankId);
					if(customerBank!= null) {
						Customer customer = employeeFunctionsService.getCustomerAccountByCustomerID(customerBank.getCustomerId());
						log.info("Customer Account Details with bank id = " + bankId);
						log.info(customer.printCustomerInfo());
						log.info(customerBank.printBankInfo());
					}		
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 6:
				log.info("Loading all transaction...");
				log.info("---------------------------------------");
				try {
					List<Transaction> transactions = employeeFunctionsService.viewAllTransactions();
					if(transactions!=null && transactions.size()>0) {
						for(Transaction t: transactions) {
							log.info(t.toString());
						}
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 7:
				log.info("Logout employee account. Thank for your service");
				break;
			default:
				log.error("Invalid Choice!!!! Please enter choice between 1-3 only");
				break;
			}		
		} while(ch != 7);
	}
}
