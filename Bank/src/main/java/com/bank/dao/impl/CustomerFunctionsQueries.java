package com.bank.dao.impl;

public class CustomerFunctionsQueries {
	
	public static final String INSERT_BANK_ACCOUNT = "INSERT INTO project.bank_account (customer_id, bank_type, balance, status, alias_name) "
			+ "VALUES(?,?,?,?,?)";
	
	public static final String GET_BANK_BY_BANK_ID = "SELECT id, customer_id, card_number, bank_type, balance, status, alias_name, account_number"
			+ " FROM project.bank_account WHERE id=?";
	
	public static final String GET_BANK_BY_ACCOUNT_NUMBER = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE account_number=?";
	
	public static final String GET_BANK_BY_CUSTOMER_ID = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE customer_id=? ORDER BY id ASC";
	
	public static final String GET_ACTIVE_BANK = "SELECT id,customer_id,card_number,bank_type,balance,status,alias_name,account_number"
			+ " FROM project.bank_account WHERE status=? AND customer_id=? ORDER BY id ASC";
	
	public static final String UPDATE_BALANCE_BY_ACCOUNT_NUMBER = "UPDATE project.bank_account SET balance=? WHERE account_number=? AND status=?";
	
	public static final String INSERT_A_TRANSACTION = "INSERT INTO project.transaction (bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive) "
			+ "VALUES(?,?,?,?,?,?)";
	
	public static final String GET_CUSTOMER_FIRSTNAME_BY_ID = "SELECT first_name FROM project.customer_account WHERE id=?";
	
	public static final String GET_TRANSACTION_BY_SENDER_BANK_ID = "SELECT id, bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive"
			+ "	FROM project.transaction WHERE bank_sender_id=?";
	
	public static final String GET_TRANSACTION_BY_RECEIVER_BANK_ID = "SELECT id, bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive"
			+ "	FROM project.transaction WHERE bank_receiver_id=?";
	
	public static final String UPDATE_TRANSACTION_STATUS_BY_ID = "UPDATE project.transaction SET status=?, date_receive=? WHERE	id=?";
}
