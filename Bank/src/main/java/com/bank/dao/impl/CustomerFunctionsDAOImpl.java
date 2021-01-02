package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerFunctionsDAO;
import com.bank.dbutil.PostresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class CustomerFunctionsDAOImpl implements CustomerFunctionsDAO {
	private static Logger log=Logger.getLogger(CustomerFunctionsDAOImpl.class);
	
	@Override
	public int applyBankAccount(int customerID, String bankType, double balance, String status,
			String aliasName) throws BusinessException {
		int c = 0;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.INSERT_BANK_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			preparedStatement.setString(2, bankType);
			preparedStatement.setDouble(3, balance);
			preparedStatement.setString(4, status);
			preparedStatement.setString(5, aliasName);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Error occured.. Please try again or kindly contact SYSADMIN");
		}
		return c;
	}
	
	@Override
	public BankAccount getBankByBankID(int bankID) throws BusinessException {
		BankAccount bankAccount=null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_BANK_BY_BANK_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bankID);	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				
			}else {
				throw new BusinessException("System can't find any bank account that matchs with card number " + bankID);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccount;
	}
	
	@Override
	public BankAccount getBankByAccountNumber(int accountNumber) throws BusinessException {
		BankAccount bankAccount=null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_BANK_BY_ACCOUNT_NUMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));		
			}else {
				throw new BusinessException("System can't find any bank account that matchs with account number " + accountNumber);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccount;
	}
	
	@Override
	public List<BankAccount> getBanksByID(int customerID) throws BusinessException {
		List<BankAccount> bankAccounts = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_BANK_BY_CUSTOMER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BankAccount bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				bankAccounts.add(bankAccount);
			}
			if(bankAccounts.size()==0)
			{
				throw new BusinessException("No Bank Records Available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccounts;
	}


	@Override
	public List<BankAccount> getActiveBankByID(int customerID) throws BusinessException {
		List<BankAccount> bankAccounts = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_ACTIVE_BANK;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "approve");
			preparedStatement.setInt(2, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BankAccount bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				bankAccounts.add(bankAccount);
			}
			if(bankAccounts.size()==0)
			{
				throw new BusinessException("No Bank Records Available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccounts;
	}

	@Override
	public int updateNewBankBalance(int accountNumber, double balance) throws BusinessException {
		int c=0;
		String status="approve";
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.UPDATE_BALANCE_BY_ACCOUNT_NUMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, balance);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.setString(3, status);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public String getCustomerFirstNameById(int customerID) throws BusinessException {
		String firstName;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_CUSTOMER_FIRSTNAME_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				firstName = resultSet.getString("first_name");
				
			}else {
				throw new BusinessException("Sorry. System can't find any Customer record for ID " +  customerID);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return firstName;
	}
	
	@Override
	public int transfer(int bankSenderId, int bankReceiverId, double amount, String status, String type) throws BusinessException {
		int c=0;
		LocalDate localDate = LocalDate.now();
		Date today = Date.valueOf(localDate);
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.INSERT_A_TRANSACTION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bankSenderId);
			preparedStatement.setInt(2, bankReceiverId);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setString(4, status);
			preparedStatement.setDate(5, today);
			if(type.equalsIgnoreCase("internal")) {
				preparedStatement.setDate(6, today);
			} else {
				preparedStatement.setDate(6, null);
			}
			c = preparedStatement.executeUpdate();			
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Error occured.. Please try again or kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public int acceptTransfer(int transferID) throws BusinessException {
		int c=0;
		String status="accepted";
		LocalDate localDate = LocalDate.now();
		Date today = Date.valueOf(localDate);
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.UPDATE_TRANSACTION_STATUS_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setDate(2, today);
			preparedStatement.setInt(3, transferID);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}
	
	@Override
	public List<Transaction> getTransactionsBySenderBankId(int bankID) throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_TRANSACTION_BY_SENDER_BANK_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bankID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction(resultSet.getInt("id"), resultSet.getInt("bank_sender_id"), resultSet.getInt("bank_receiver_id"), 
						resultSet.getDouble("amount"), resultSet.getString("status"), resultSet.getDate("date_send"), resultSet.getDate("date_receive"));
				transactionList.add(transaction);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return transactionList;
	}

	@Override
	public List<Transaction> getTransactionsByReceiverBankId(int bankID) throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = CustomerFunctionsQueries.GET_TRANSACTION_BY_RECEIVER_BANK_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bankID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction(resultSet.getInt("id"), resultSet.getInt("bank_sender_id"), resultSet.getInt("bank_receiver_id"), 
						resultSet.getDouble("amount"), resultSet.getString("status"), resultSet.getDate("date_send"), resultSet.getDate("date_receive"));
				transactionList.add(transaction);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return transactionList;
	}
}
