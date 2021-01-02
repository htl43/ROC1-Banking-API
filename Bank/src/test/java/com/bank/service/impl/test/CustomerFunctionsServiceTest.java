package com.bank.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BusinessException;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.service.CustomerFunctionsService;
import com.bank.service.impl.CustomerFunctionsServiceImpl;

class CustomerFunctionsServiceTest {
	
	private static CustomerFunctionsService customerFunctionsService;
	
	@BeforeAll
	public static void createUserService() throws BusinessException {
		customerFunctionsService = new CustomerFunctionsServiceImpl();
	}
	
	@Test
	void testValidDeposit() throws BusinessException {
		
		BankAccount testBankAccount = customerFunctionsService.getBankByBankID(13);			
		double oldBalance= testBankAccount.getBalance();
		double newBalance= customerFunctionsService.deposit(testBankAccount.getAccountNumber(), 300);
		assertEquals(newBalance, oldBalance + 300);
		
	}
	
	@Test
	void testInvalidDeposit() {
		BankAccount testBankAccount;
		try {
			testBankAccount = customerFunctionsService.getBankByBankID(13);
			double newBalance= customerFunctionsService.deposit(testBankAccount.getAccountNumber(), -300);

		} catch (BusinessException e) {
			assertNotNull(e.getMessage());
			System.out.println(e.getMessage());
		}			
		
		
		
	}

	@Test
	void testValidWithdrawal() throws BusinessException {
		
		BankAccount testBankAccount = customerFunctionsService.getBankByBankID(13);
		double oldBalance = testBankAccount.getBalance();
		double newBalance = customerFunctionsService.withdrawal(testBankAccount.getAccountNumber(), 100);
		assertEquals(newBalance, oldBalance - 100);			
		
	}
	
	@Test
	void testInvalidWithdrawal() {
		try {
			BankAccount testBankAccount = customerFunctionsService.getBankByBankID(13);
			double newBalance = customerFunctionsService.withdrawal(testBankAccount.getAccountNumber(), -100);
					
		} catch (BusinessException e) {
			assertNotNull(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		try {
			BankAccount testBankAccount = customerFunctionsService.getBankByBankID(13);
			double newBalance = customerFunctionsService.withdrawal(testBankAccount.getAccountNumber(), 5001);		
		} catch (BusinessException e) {
			assertNotNull(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		try {
			BankAccount testBankAccount = customerFunctionsService.getBankByBankID(14);
			double oldBalance = testBankAccount.getBalance();
			double amount = oldBalance + 1;
			double newBalance = customerFunctionsService.withdrawal(testBankAccount.getAccountNumber(), amount);		
		} catch (BusinessException e) {
			assertNotNull(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	@Test
	void testValidInternalTransfer() throws BusinessException {
		int customerIDTest =  1059;
		List<BankAccount> bankList = customerFunctionsService.getActiveBankByID(customerIDTest);
		assertNotNull(bankList);
		assertEquals(true, bankList.size()>1);
		int rs = 0;
		rs = customerFunctionsService.transfer(bankList.get(0).getId(), bankList.get(1).getId(), 100, "accepted", "internal");
		assertEquals(true, rs > 0);
	}
	
	@Test
	void testValidExternalTransfer() throws BusinessException {
		int senderId = 1059;
		int receiverId = 1060;
		List<BankAccount> senderBankList = customerFunctionsService.getActiveBankByID(senderId);
		List<BankAccount> receiverBankList = customerFunctionsService.getActiveBankByID(receiverId);
		assertNotNull(senderBankList);
		assertNotNull(receiverBankList);
		assertEquals(true, senderBankList.size()>0);
		assertEquals(true, receiverBankList.size()>0);
		int rs = 0;
		rs = customerFunctionsService.transfer(senderBankList.get(0).getId(), receiverBankList.get(0).getId(), 100, "pending", "external");
		assertEquals(true, rs > 0);
	}

	@Test
	void testValidAcceptTransfer() throws BusinessException {
		int receiverId = 1060;
		List<BankAccount> receiverBankList = customerFunctionsService.getActiveBankByID(receiverId);
		assertNotNull(receiverBankList);
		assertEquals(true, receiverBankList.size()>0);
		BankAccount selBankAccount = receiverBankList.get(0);
		List<Transaction> receiverTransactions = customerFunctionsService.getTransactionsByReceiverBankId(selBankAccount.getId());
		assertNotNull(receiverTransactions);
		assertEquals(receiverTransactions.size()>0, true);
		int rs = 0;
		Transaction selTransaction = receiverTransactions.get(0);
		rs = customerFunctionsService.acceptTransfer(selTransaction.getId());
		assertEquals(rs>0, true);
		
	}

}
