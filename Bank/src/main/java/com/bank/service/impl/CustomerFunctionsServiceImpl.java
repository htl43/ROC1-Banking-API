package com.bank.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerFunctionsDAO;
import com.bank.dao.impl.CustomerFunctionsDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.service.CustomerFunctionsService;

public class CustomerFunctionsServiceImpl implements CustomerFunctionsService {
	private static Logger log=Logger.getLogger(CustomerFunctionsServiceImpl.class);
	CustomerFunctionsDAO customerFunctions = new CustomerFunctionsDAOImpl();
	
	@Override
	public int applyBankAccount(int customerID, double balance, String bankType, String aliasName) throws BusinessException {
		if (balance < 200) {
			throw new BusinessException("Initial deposit for apply bank account is at least $200");
		}
		int c;
		String status="spending";
		c = customerFunctions.applyBankAccount(customerID, bankType, balance, status, aliasName);		
		return c;
	}

	@Override
	public List<BankAccount> getBanksByID(int customerID) throws BusinessException {
		List<BankAccount> bankAccounts = null;
		if(customerID > 0) {
			bankAccounts = customerFunctions.getBanksByID(customerID);
		} else {
			throw new BusinessException("Invalid for customer id " + customerID);
		}
		return bankAccounts;
	}

	@Override
	public List<BankAccount> getActiveBankByID(int customerID) throws BusinessException {
		List<BankAccount> bankAccounts = null;
		if(customerID > 0) {
			bankAccounts = customerFunctions.getActiveBankByID(customerID);
		} else {
			throw new BusinessException("Invalid for customer id " + customerID);
		}
		return bankAccounts;
	}

	@Override
	public BankAccount getBankByBankID(int bankID) throws BusinessException {
		if(bankID <= 0) {
			throw new BusinessException("Invalid for a negative account number " + bankID);
		}
		BankAccount bankAccount = customerFunctions.getBankByBankID(bankID);
		return bankAccount;
	}

	@Override
	public BankAccount getBankByAccountNumber(int accountNumber) throws BusinessException {
		if(accountNumber <= 0) {
			throw new BusinessException("Invalid for a negative account number " + accountNumber);
		}
		BankAccount bankAccount = customerFunctions.getBankByAccountNumber(accountNumber);
		return bankAccount;
	}

	@Override
	public double deposit(int accountNumber, double amount) throws BusinessException {
		double balance = -1;
		if(amount <= 0) {
			throw new BusinessException("Invalid amout of $" + amount + " for the deposit");
		} else {
			BankAccount bankAccount = customerFunctions.getBankByAccountNumber(accountNumber);
			if(bankAccount!=null && bankAccount.getStatus().equalsIgnoreCase("approve")) {
				balance = bankAccount.getBalance() + amount;
				customerFunctions.updateNewBankBalance(accountNumber, balance);
			} else {
				throw new BusinessException("System can't find any bank record with the account number " + accountNumber);
			}
			
		}
		return balance;
	}

	@Override
	public double withdrawal(int accountNumber, double amount) throws BusinessException {
		double balance = -1;
		if(amount <= 0) {
			throw new BusinessException("Invalid amout of $" + amount + " for the deposit");
		} else if (amount > 5000){
			throw new BusinessException("Sorry. The amout must not be greater than $5000 for every withdrawal.");
		}
		else {
			BankAccount bankAccount = customerFunctions.getBankByAccountNumber(accountNumber);
			if(bankAccount!=null && bankAccount.getStatus().equalsIgnoreCase("approve")) {
				if(bankAccount.getBalance() >= amount) {
					 balance = bankAccount.getBalance() - amount;
					customerFunctions.updateNewBankBalance(accountNumber, balance);
				} else {
					throw new BusinessException("The bank with account number " + accountNumber + " has balance=$" + bankAccount.getBalance()
					 + " is smaller than the amount=" + amount + " of your withdrawal");
				}
			} else {
				throw new BusinessException("System can't find any bank record with the account number " + accountNumber);
			}
			
		}
		return balance;
	}

	@Override
	public int transfer(int bankSenderId, int bankReceiverId, double amount, String status, String type) throws BusinessException {
		int c = 0;
		c = customerFunctions.transfer(bankSenderId, bankReceiverId, amount, status, type);
		return c;
	}

	@Override
	public int acceptTransfer(int transferID) throws BusinessException {
		if(transferID<=0) {
			throw new BusinessException("Invalid value for transaction id=" + transferID);
		}
		return customerFunctions.acceptTransfer(transferID);
	}

	@Override
	public String getCustomerFirstNameById(int customerID) throws BusinessException {
		if(customerID <= 0) {
			throw new BusinessException("Invalid for customer id= " + customerID + " is smaller or equal 0");
		} else {
			return customerFunctions.getCustomerFirstNameById(customerID);		
		}
	}

	@Override
	public List<Transaction> getTransactionsBySenderBankId(int bankID) throws BusinessException {
		if(bankID < 0) {
			throw new BusinessException("Invalid for customer id= " + bankID + " is smaller or equal 0");
		} else {
			return customerFunctions.getTransactionsBySenderBankId(bankID);		
		}
	}

	@Override
	public List<Transaction> getTransactionsByReceiverBankId(int bankID) throws BusinessException {
		if(bankID < 0) {
			throw new BusinessException("Invalid for customer id= " + bankID + " is smaller or equal 0");
		} else {
			return customerFunctions.getTransactionsByReceiverBankId(bankID);		
		}
	}

}
