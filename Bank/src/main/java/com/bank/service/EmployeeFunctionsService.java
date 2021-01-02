package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public interface EmployeeFunctionsService {
	
		public List<BankAccount> viewAllBankAccounts() throws BusinessException;
		public List<BankAccount> viewApplyBankAccounts() throws BusinessException;
		public List<BankAccount> viewActiveBankAccounts() throws BusinessException;
		
		public List<BankAccount> getBanksByCustomerID(int customerID) throws BusinessException;
		public Customer getCustomerAccountByCustomerID(int customerID) throws BusinessException;
		
		public BankAccount getBankAccountById(int id) throws BusinessException;
		public int updateBankAccountStatusById(int id, String status) throws BusinessException;
		public BankAccount getBankAccountByAccountNumber(int accountNumber) throws BusinessException;
		public int updateBankAccountNumberById(int id, int accountNumber) throws BusinessException;
		public BankAccount getBankAccountByCardNumber(String cardNumber) throws BusinessException;
		public int updateBankCardNumberById(int id, String cardNumber) throws BusinessException;
		
		public List<Transaction> viewAllTransactions() throws BusinessException;
		public List<Transaction> viewAllTransactionsDetails() throws BusinessException;
}
