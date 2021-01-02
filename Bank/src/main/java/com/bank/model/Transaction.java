package com.bank.model;

import java.sql.Date;

public class Transaction {
	private int id;
	private int bankSenderId;
	private int bankReceiverId;
	private double amount;
	private String status;
	private Date sendDate;
	private Date receivedDate;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, int bankSenderId, int bankReceiverId, double amount, String status, Date sendDate,
			Date receivedDate) {
		super();
		this.id = id;
		this.bankSenderId = bankSenderId;
		this.bankReceiverId = bankReceiverId;
		this.amount = amount;
		this.status = status;
		this.sendDate = sendDate;
		this.receivedDate = receivedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBankSenderId() {
		return bankSenderId;
	}

	public void setBankSenderId(int bankSenderId) {
		this.bankSenderId = bankSenderId;
	}

	public int getBankReceiverId() {
		return bankReceiverId;
	}

	public void setBankReceiverId(int bankReceiverId) {
		this.bankReceiverId = bankReceiverId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", bankSenderId=" + bankSenderId + ", bankReceiverId=" + bankReceiverId
				+ ", amount=" + amount + ", status=" + status + ", sendDate=" + sendDate + ", receivedDate="
				+ receivedDate + "]";
	}	
	
	public String printInfo() {
		return "Transaction [id=" + id + ", amount=" + amount + ", status=" + status + ", sendDate=" + sendDate + ", receivedDate="
				+ receivedDate + "]";
	}	
}
