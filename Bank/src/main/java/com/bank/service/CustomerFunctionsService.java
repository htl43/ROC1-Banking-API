package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;

public interface CustomerFunctionsService {	
	
		public int applyBankAccount(int customerID, double balance, String bankType, String aliasName) throws BusinessException;
		public BankAccount getBankByBankID(int bankID) throws BusinessException;
		public BankAccount getBankByAccountNumber(int accountNumber) throws BusinessException;
		public List<BankAccount> getBanksByID(int customerID) throws BusinessException;
		public List<BankAccount> getActiveBankByID(int customerID) throws BusinessException;
		public String getCustomerFirstNameById(int customerID) throws BusinessException;

		public double deposit(int accountNumber, double amount) throws BusinessException;
		public double withdrawal(int accountNumber, double amount) throws BusinessException;
		
		public List<Transaction> getTransactionsBySenderBankId(int bankID) throws BusinessException;
		public List<Transaction> getTransactionsByReceiverBankId(int bankID) throws BusinessException;
		public int transfer(int bankSenderId, int bankReceiverId, double amount, String status, String type) throws BusinessException;
		public int acceptTransfer(int transferID) throws BusinessException;
}
