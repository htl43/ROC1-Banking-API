package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeFunctionsDAO;
import com.bank.dbutil.PostresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class EmployeeFunctionsDAOImpl implements EmployeeFunctionsDAO{

	private static Logger log=Logger.getLogger(EmployeeFunctionsDAOImpl.class);
	
	@Override
	public List<BankAccount> viewAllBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_ALL_CUSTOMER_BANK_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
	public List<BankAccount> viewApplyBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_BANK_ACCOUNT_BY_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "spending");
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
	public List<BankAccount> viewActiveBankAccounts() throws BusinessException {
		List<BankAccount> bankAccounts = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_BANK_ACCOUNT_BY_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "approve");
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
	public BankAccount getBankAccountById(int id) throws BusinessException {
		BankAccount bankAccount=null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_BANK_ACCOUNT_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				
			}else {
				throw new BusinessException("System can't find any bank account that matchs with id " + id);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccount;
	}

	@Override
	public BankAccount getBankAccountByAccountNumber(int accountNumber) throws BusinessException {
		BankAccount bankAccount=null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_BANK_ACCOUNT_BY_ACCOUNT_NUMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccount;
	}

	@Override
	public BankAccount getBankAccountByCardNumber(String cardNumber) throws BusinessException {
		BankAccount bankAccount=null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_BANK_ACCOUNT_BY_CARD_NUMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cardNumber);	
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bankAccount = new BankAccount(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("customer_id"), 
						resultSet.getString("card_number"), resultSet.getString("bank_type"), resultSet.getDouble("balance"), 
						resultSet.getString("status"), resultSet.getString("alias_name"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return bankAccount;
	}

	@Override
	public int updateBankAccountStatusById(int id, String status) throws BusinessException {
		int c=0;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.UPDATE_CUSTOMER_BANK_ACCOUNT_STATUS_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, id);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}
	
	@Override
	public int updateBankAccountNumberById(int id, int accountNumber) throws BusinessException {
		int c=0;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.UPDATE_CUSTOMER_BANK_ACCOUNT_NUBMER_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, id);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public int updateBankCardNumberById(int id, String cardNumber) throws BusinessException {
		int c=0;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.UPDATE_CUSTOMER_BANK_ACCOUNT_CARD_NUBMER_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cardNumber);
			preparedStatement.setInt(2, id);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}
	
	@Override
	public Customer getCustomerAccountByCustomerID(int customerID) throws BusinessException {
		Customer customer = null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_CUSTOMER_ACCOUNT_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone"));
				customer.setId(resultSet.getInt("id"));
			}else {
				throw new BusinessException("Sorry. System can't find any Customer record for ID " +  customerID);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public List<BankAccount> getBanksByCustomerID(int customerID) throws BusinessException {
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
	public List<Transaction> viewAllTransactions() throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = EmployeeFunctionsQueries.GET_ALL_TRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction(resultSet.getInt("id"), resultSet.getInt("bank_sender_id"), resultSet.getInt("bank_receiver_id"), 
						resultSet.getDouble("amount"), resultSet.getString("status"), resultSet.getDate("date_send"), resultSet.getDate("date_receive"));
				transactionList.add(transaction);
			}
			if(transactionList.size()==0)
			{
				throw new BusinessException("No Transaction Records Available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return transactionList;
	}
	
	@Override
	public List<Transaction> viewAllTransactionsDetails() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
