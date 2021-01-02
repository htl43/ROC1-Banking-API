package com.bank.dao.impl;

public class EmployeeFunctionsQueries {
	
	public static final String GET_ALL_CUSTOMER_BANK_ACCOUNT = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account ORDER BY id ASC";
	
	public static final String GET_CUSTOMER_BANK_ACCOUNT_BY_STATUS = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE status=? ORDER BY id ASC";
		
	public static final String GET_CUSTOMER_BANK_ACCOUNT_BY_ID = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE id=?";
	
	public static final String GET_CUSTOMER_BANK_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE account_number=?";
	
	public static final String GET_CUSTOMER_BANK_ACCOUNT_BY_CARD_NUMBER = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE card_number=?";
	
	public static final String UPDATE_CUSTOMER_BANK_ACCOUNT_STATUS_BY_ID = "UPDATE project.bank_account SET status=? WHERE id=?";
	
	public static final String UPDATE_CUSTOMER_BANK_ACCOUNT_NUBMER_BY_ID = "UPDATE project.bank_account SET account_number=? WHERE id=?";
	
	public static final String UPDATE_CUSTOMER_BANK_ACCOUNT_CARD_NUBMER_BY_ID = "UPDATE project.bank_account SET card_number=? WHERE id=?";
	
	public static final String GET_CUSTOMER_ACCOUNT_BY_ID = "SELECT id, username, password, first_name, last_name, email,phone "
			+ "FROM project.customer_account WHERE id=?";
	
	public static final String GET_ALL_TRANSACTIONS = "SELECT id, bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive"
			+ " FROM project.transaction ORDER BY id ASC";
}
