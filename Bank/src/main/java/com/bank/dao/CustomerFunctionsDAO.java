package com.bank.dao;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;

public interface CustomerFunctionsDAO {
	
	// finish methods
	public int applyBankAccount(int customerID, String bankType, double balance, String status,
			String aliasName) throws BusinessException;
	public BankAccount getBankByBankID(int bankID) throws BusinessException;
	public BankAccount getBankByAccountNumber(int accountNumber) throws BusinessException;
	public List<BankAccount> getBanksByID(int customerID) throws BusinessException;
	public List<BankAccount> getActiveBankByID(int customerID) throws BusinessException;
	public int updateNewBankBalance(int accountNumber, double balance) throws BusinessException;
	
	public String getCustomerFirstNameById(int customerID) throws BusinessException;
	
	public List<Transaction> getTransactionsBySenderBankId(int bankID) throws BusinessException;
	public List<Transaction> getTransactionsByReceiverBankId(int bankID) throws BusinessException;
	public int transfer(int bankSenderId, int bankReceiverId, double amount, String status, String type) throws BusinessException;
	public int acceptTransfer(int transferID) throws BusinessException;
	
}
