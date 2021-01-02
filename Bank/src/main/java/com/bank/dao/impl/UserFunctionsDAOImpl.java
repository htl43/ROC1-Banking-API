package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.UserFunctionsDAO;
import com.bank.dbutil.PostresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public class UserFunctionsDAOImpl implements UserFunctionsDAO{
	
	private static Logger log=Logger.getLogger(UserFunctionsDAOImpl.class);

	@Override
	public int registerCustomerAccount(Customer customer) throws BusinessException {
		int c = 0;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = UserFunctionsQueries.INSERT_CUSTOMER_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getFirstName());
			preparedStatement.setString(4, customer.getLastName());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPhone());
			
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Error occured.. Please try again or kindly contact SYSADMIN");
		}
		return c;
	}

	@Override
	public Customer loginCustomerAccount(String username, String password) throws BusinessException {
		Customer customer = null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = UserFunctionsQueries.LOGIN_CUSTOMER_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone"));
				customer.setId(resultSet.getInt("id"));
			}else {
				throw new BusinessException("Sorry. System can't find any record that matchs with Customer username and password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return customer;
	}

	@Override
	public Employee loginEmployeeAccount(String username, String password) throws BusinessException {
		Employee employee = null;
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = UserFunctionsQueries.LOGIN_EMPLOYEE_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("first_name"), 
						resultSet.getString("last_name"), resultSet.getString("position"), resultSet.getString("department"));
				employee.setId(resultSet.getInt("id"));
			}else {
				throw new BusinessException("Sorry. System can't find any record that matchs with Employee username and password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
		return employee;
	}

}
