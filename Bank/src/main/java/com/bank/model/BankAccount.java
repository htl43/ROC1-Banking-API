package com.bank.model;

public class BankAccount {
	private int id;
	private int customerId;
	private String cardNumber;
	private String bankType;
	private double balance;
	private String status;
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	private String aliasName;
	private int accountNumber;
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public BankAccount(int id, int accountNumber, int customerId, String cardNumber, String bankType, double balance,
			String status, String aliasName) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.cardNumber = cardNumber;
		this.bankType = bankType;
		this.balance = balance;
		this.status = status;
		this.aliasName = aliasName;
	}

	public BankAccount(int customerId, String bankType, double balance, String status, String aliasName) {
		super();
		this.customerId = customerId;
		this.bankType = bankType;
		this.balance = balance;
		this.status = status;
		this.aliasName = aliasName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getcardNumber() {
		return cardNumber;
	}

	public void setcardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", accountNumber=" + accountNumber + ", customerId=" + customerId
				+ ", cardNumber=" + cardNumber + ", bankType=" + bankType + ", balance=" + balance + ", status="
				+ status + ", aliasName=" + aliasName + "]";
	}

	public String printBankInfo() {
		return "BankAccount [accountNumber=" + accountNumber + ", cardNumber=" + cardNumber + ", bankType=" + bankType
				+ ", balance=" + balance + ", status=" + status + ", aliasName=" + aliasName + "]";
	}
}
