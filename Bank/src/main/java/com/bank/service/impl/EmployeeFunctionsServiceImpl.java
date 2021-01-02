package com.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.postgresql.core.TransactionState;

import com.bank.dao.CustomerFunctionsDAO;
import com.bank.dao.EmployeeFunctionsDAO;
import com.bank.dao.impl.CustomerFunctionsDAOImpl;
import com.bank.dao.impl.EmployeeFunctionsDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.service.EmployeeFunctionsService;

public class EmployeeFunctionsServiceImpl implements EmployeeFunctionsService {
	
	private static Logger log=Logger.getLogger(EmployeeFunctionsServiceImpl.class);
	EmployeeFunctionsDAO employeeFunctions = new EmployeeFunctionsDAOImpl();

	@Override
	public List<BankAccount> viewAllBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = employeeFunctions.viewAllBankAccounts();
		return bankAccounts;
	}

	@Override
	public List<BankAccount> viewApplyBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = employeeFunctions.viewApplyBankAccounts();
		return bankAccounts;
	}

	@Override
	public List<BankAccount> viewActiveBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = employeeFunctions.viewActiveBankAccounts();
		return bankAccounts;
	}

	@Override
	public int updateBankAccountStatusById(int id, String status) throws BusinessException {
		if(id < 0) {
			throw new BusinessException("Invalid value for id=" + id);
		}
		int c=0;
		BankAccount bankAccount = employeeFunctions.getBankAccountById(id);
		// Check if emplyee approve for a new applying bank account
		if(bankAccount!= null) {
			if(bankAccount.getStatus().equalsIgnoreCase("spending") && status.equalsIgnoreCase("approve")) {
				String cardNumber;
				int accountNumber;
				boolean check=true;
				// generate 16 digits String card number
				do {
					cardNumber = "";
					Random random = new Random();
					for(int i=1; i<= 16; i++) {
						cardNumber += random.nextInt(9);
					}
					// check if a exist bank with this card number	
					BankAccount tmp = employeeFunctions.getBankAccountByCardNumber(cardNumber);
					if(tmp == null) {				
						c = employeeFunctions.updateBankCardNumberById(id, cardNumber);
						break;
					}
				} while(check);
				
				do {
					String s = "";
					Random random = new Random();
					for(int i=1; i<= 8; i++) {
						s += random.nextInt(9);
					}
					accountNumber = Integer.parseInt(s);
					// check if a exist bank with this account number
					BankAccount tmp = employeeFunctions.getBankAccountByAccountNumber(accountNumber);
					if(tmp==null) {
						employeeFunctions.updateBankAccountNumberById(id, accountNumber);
						break;
					}
				} while(check);
			}
		} else {
			throw new BusinessException("System can't find any bank record with id " + id);
		}
		c = employeeFunctions.updateBankAccountStatusById(id, status);
		return c;
	}

	@Override
	public BankAccount getBankAccountById(int id) throws BusinessException {
		if(id < 0) {
			throw new BusinessException("Invalid value for id=" + id);
		}
		BankAccount bankAccount = employeeFunctions.getBankAccountById(id);
		return bankAccount;
	}
	
	@Override
	public Customer getCustomerAccountByCustomerID(int customerID) throws BusinessException {
		if(customerID < 0) {
			throw new BusinessException("Invalid value for id=" + customerID);
		}
		Customer customer = employeeFunctions.getCustomerAccountByCustomerID(customerID);
		return customer;
	}

	@Override
	public List<BankAccount> getBanksByCustomerID(int customerID) throws BusinessException {
		List<BankAccount> bankAccounts = employeeFunctions.getBanksByCustomerID(customerID);
		return bankAccounts;
	}

	@Override
	public BankAccount getBankAccountByAccountNumber(int accountNumber) throws BusinessException {
		if(accountNumber < 0) {
			throw new BusinessException("Invalid value for id=" + accountNumber);
		}
		BankAccount bankAccount = employeeFunctions.getBankAccountByAccountNumber(accountNumber);
		return bankAccount;
	}

	@Override
	public int updateBankAccountNumberById(int id, int accountNumber) throws BusinessException {
		if(accountNumber < 0 || id < 0) {
			throw new BusinessException("Invalid value for id or account number");
		}
		int c = employeeFunctions.updateBankAccountNumberById(id, accountNumber);
		return c;
	}

	
	@Override
	public List<Transaction> viewAllTransactions() throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		transactionList = employeeFunctions.viewAllTransactions();
		return transactionList;
	}
	
	@Override
	public List<Transaction> viewAllTransactionsDetails() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BankAccount getBankAccountByCardNumber(String cardNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBankCardNumberById(int id, String cardNumber) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
