package com.bank.account.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.main.BankingMain;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.service.CustomerFunctionsService;
import com.bank.service.impl.CustomerFunctionsServiceImpl;

public class CustomerAccountLogin {
	private static Logger log=Logger.getLogger(CustomerAccountLogin.class);
	
	public static void activateAccount(Customer customer) {	
		CustomerFunctionsService customerFunctionsService = new CustomerFunctionsServiceImpl();
		Scanner scanner = new Scanner(System.in);
		int ch = 0;
		log.info(customer.printCustomerInfo());
		log.info("--------------------------------------------------");	
		do {
			log.info("\nCustomer Account Menu, Please select one option below");
			log.info("=========================================");
			log.info("1)View Customer Info Details ");
			log.info("2)Apply for a new Bank Account");
			log.info("3)View all bank accounts' details");
			log.info("4)Deposit or WithDrawal to a bank account");
			log.info("5)Make A Transaction");
			log.info("6)Transactions Center");
			log.info("7)Logout");
			try {
				ch = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				log.warn(e.getMessage() + " is Invalid");
				continue;
			}
			switch (ch) {
			case 1:
				log.info("\nCustomer Deatil Information");
				log.info("-------------------------------");
				log.info(customer.toString());
				break;
			case 2:
				String bankType="";
				double balance=0;
				String aliasName;		
				log.info("\nApply for a new Bank account. Please follow the instruction");
				log.info("---------------------------");
				int ch1=0;
				do {
					log.info("Please select a number from 1-4 for bank's type, select 5 to GO BACK");
					log.info("1)Checking");
					log.info("2)Saving");
					log.info("3)Investment");
					log.info("4)Retirement");
					log.info("5)GO BACK");
					try {
						ch1 = Integer.parseInt(scanner.nextLine().trim());
						
					} catch (NumberFormatException e) {
						log.warn(e.getMessage() + " is Invalid");
						continue;
					} 
					switch (ch1) {
					case 1:
						bankType="Checking";
						break;
					case 2:
						bankType="Saving";
						break;
					case 3:
						bankType="Investment";
						break;
					case 4:
						bankType="Retirement";
						break;
					case 5:
						log.info("Back to Customer Account Menu...");
						break;
					default:
						log.error("Invalid Choice!!!! Please enter choice between 1-5 only");
						break;
					}
					if(bankType!="") {
						break;
					}
				} while(ch1!=5);
				if(ch1==5) {
					break;
				}
				boolean checkBalance=false;
				do {
					log.info("\nInial balance for a new bank account must be greater than 200. Please enter the amount"
							+ " that you want to deposit today");
					try {
						balance = Double.parseDouble(scanner.nextLine().trim());
						checkBalance = true;
					} catch (NumberFormatException e) {
						log.warn(e.getMessage() + " is Invalid");
						continue;
					}
				}while(!checkBalance);
				log.info("Enter the alias name for your bank. Keep blank if you don't want");
				aliasName = scanner.nextLine().trim();
				do {
					log.info("Review your application. Type Y/y to confirm or type N/n for cancel");
					log.info("----------------------------------------------------------------");
					log.info("Bank type: " + bankType + ", Initial deposit: " + balance + ", Alias name: " + aliasName);
					String s = scanner.nextLine().trim();
					if(s.equalsIgnoreCase("y")) {
						try {
							if(customerFunctionsService.applyBankAccount(customer.getId(), balance, bankType, aliasName)>0) {
								log.info("Your application has been submitted and will be approved or rejected by our employee in a few business days");
								log.info("CustomerID=" + customer.getId() + ", balance=" + balance + ", bankType=" + bankType
										+ ",aliasName=" + aliasName);
							} 
							break;
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
					} else if (s.equalsIgnoreCase("n")) {
						log.info("Cancel for apply new bank account...");
						break;
					} else {
						log.error("Invalid input value. Please try again...");
						continue;
					}
					break;
				} while(true);
				break;
			case 3:
				log.info("\nLoading bank accounts...");
				log.info("---------------------------");
				try {
					List<BankAccount> bankAccounts=customerFunctionsService.getBanksByID(customer.getId());
					List<BankAccount> activeAccounts= new ArrayList<>();
					List<BankAccount> inActiveAccounts= new ArrayList<>();
					if(bankAccounts!=null && bankAccounts.size()>0) {
						for(BankAccount b:bankAccounts) {
							if(b.getStatus().equalsIgnoreCase("approve")) {
								activeAccounts.add(b);
							} else {
								inActiveAccounts.add(b);
							}
						}
						if(activeAccounts.size()>0) {
							log.info("You have " + activeAccounts.size() + " active bank accounts");
							for(BankAccount b:activeAccounts) {
								log.info(b.printBankInfo());
							}
						}
						if(inActiveAccounts.size()>0) {
							log.info("---------------------------");
							log.info("You have " + inActiveAccounts.size() + " inactive bank accounts");
							for(BankAccount b:inActiveAccounts) {
								log.info(b.printBankInfo());
							}
						}
					} else {
						log.info("No bank record available");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 4:
				log.info("\nLoading active bank accounts...");
				log.info("---------------------------");
				try {
					List<BankAccount> bankAccounts=customerFunctionsService.getActiveBankByID(customer.getId());						
					if(bankAccounts!=null && bankAccounts.size()>0) { 
						for(int i=0; i < bankAccounts.size(); i++) {
							log.info((i+1) + ") " + bankAccounts.get(i).printBankInfo());
						}
						log.info((bankAccounts.size()+1) + ") Quit");
					} 
					int accountNumber;
					int customerChoice=0;
					double amount;
					int ch2 = 0;
					do {
						log.info("Please enter a correspoding number to select a bank. Or enter " + (bankAccounts.size()+1) + " to Quit" );
						try {
							ch2= Integer.parseInt(scanner.nextLine().trim());
						} catch (NumberFormatException e) {
							log.warn(e.getMessage() + " is Invalid.");
							continue;
						} 
						if(ch2 == (bankAccounts.size()+1)) {
							break;
						}
						if(ch2 < 1 || ch2 > bankAccounts.size()) {
							log.error("Invalid choice. Please enter a number from 1 to " + bankAccounts.size());
							continue;
						} else {
							accountNumber = bankAccounts.get(ch2-1).getAccountNumber();
							do {	
								log.info("Enter the number for your options");
								log.info("---------------------------");
								log.info("1)Deposit");
								log.info("2)Withdrawal");
								log.info("3)Quit");
								try {
									customerChoice= Integer.parseInt(scanner.nextLine().trim());
								} catch (NumberFormatException e) {
									log.warn(e.getMessage() + " is Invalid. Process will Start over");
									continue;
								} 
								if(customerChoice == 1 || customerChoice == 2) {
									log.info("Enter the amout you want");
									try {
										amount= Double.parseDouble(scanner.nextLine().trim());
										
									} catch (NumberFormatException e) {
										log.warn(e.getMessage() + " is Invalid. Process will Start over");
										continue;
									}
									if(amount <= 0) {
										log.error("Invalid value for the amount of " + amount);
										continue;
									}
									try {
										double newBalance;
										if(customerChoice == 1) {
											newBalance= customerFunctionsService.deposit(accountNumber, amount);
										} else {
											newBalance = customerFunctionsService.withdrawal(accountNumber, amount);
										}
										if(newBalance > 0) {
											log.info("New balance updated " + newBalance + " for account number= " + accountNumber);
										}
										break;
									} catch (BusinessException e) {
											log.warn(e.getCause());
									}								
								} else if (customerChoice == 3) {
									break;
								} else {
									log.error("Invalid choice. Please enter a number 1 or 2. Process will Start over");
									continue;
								}
								break;
							} while(true);
							break;
						}
					} while(true);
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 5:		
				int senderBankId;
				int receiverBankId;
				double amount5;
				int ch5=0;
				log.info("Transaction process. Loading active bank accounts");
				log.info("----------------------------------------------------");
				List<BankAccount> bankAccounts5 = null;
				try {
					bankAccounts5 = customerFunctionsService.getActiveBankByID(customer.getId());
					if(bankAccounts5!=null && bankAccounts5.size()>0) { 
						for(int i=0; i < bankAccounts5.size(); i++) {
							log.info((i+1) + ") " + bankAccounts5.get(i).printBankInfo());
						}
						log.info((bankAccounts5.size()+1) + ") Quit");
					} else {
						log.error("No Active Bank Record Available. Please contact customer service for any question");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					break;
				}
				do {				
					log.info("Please enter a correspoding number to select the sending bank. Or enter " + (bankAccounts5.size()+1) + " to Quit" );
					try {
						ch5= Integer.parseInt(scanner.nextLine().trim());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage() + " is Invalid.");
						continue;
					} 
					if(ch5 == (bankAccounts5.size()+1)) {
						break;
					}
					if(ch5 < 1 || ch5 > bankAccounts5.size()) {
						log.error("Invalid choice. Please enter a number from 1 to " + bankAccounts5.size());
						continue;
					} else {
						int bankPos = ch5 - 1;
						senderBankId = bankAccounts5.get(bankPos).getId();
						int receiverAccountNumber;
						int accountNumber5 = bankAccounts5.get(bankPos).getAccountNumber();
						do {
							log.info("Please enter the amount for transfer" );
							try {
								amount5= Double.parseDouble(scanner.nextLine().trim());
							} catch (NumberFormatException e) {
								log.warn(e.getMessage() + " is Invalid.");
								continue;
							}
							if(amount5 <= 0) {
								log.info("Invalid negative value for the amout " + amount5);
								continue;
							}
							if(amount5 > bankAccounts5.get(bankPos).getBalance()) {
								log.error("Invalid value for the transfer amount of " + amount5 + " is greater than bank balance of " + bankAccounts5.get(bankPos).getBalance());
								continue;
							} else {
								break;
							}
						} while(true);
						log.info("Please enter the receiver account number" );
						try {
							receiverAccountNumber= Integer.parseInt(scanner.nextLine().trim());
						} catch (NumberFormatException e) {
							log.warn(e.getMessage() + " is Invalid.");
							continue;
						} 				
						try {
							BankAccount receiverAccount = customerFunctionsService.getBankByAccountNumber(receiverAccountNumber);
							if(receiverAccount != null && receiverAccount.getStatus().equalsIgnoreCase("approve")) {
								receiverBankId = receiverAccount.getId();
								if(receiverAccount.getCustomerId() == customer.getId()) {
									log.info("Process internal bank accounts transfer");
									if(customerFunctionsService.transfer(senderBankId, receiverBankId, amount5, "accepted", "internal") > 0) {									
										customerFunctionsService.withdrawal(accountNumber5, amount5);
										customerFunctionsService.deposit(receiverAccountNumber, amount5);
										log.info("Finish transfer. Please check Transactin Center for details");
									}
								} else {
									String getFirstName;
									do {
										log.info("Please enter the receiver's first name" );
										getFirstName = scanner.nextLine().trim();
										if(getFirstName.isEmpty() || getFirstName==null) {
											log.error("The first name field can't be empty");
											continue;
										} else {	
											do {
												log.info("Review your application. Type Y/y to confirm or type N/n for cancel");
												log.info("----------------------------------------------------------------");
												log.info("Transaction: amount= " + amount5 + " from account number " + accountNumber5 + " to " + getFirstName
														+ " with the account number " + receiverAccountNumber);
												String s = scanner.nextLine().trim();
												if(s.equalsIgnoreCase("y")) {
													try {
														String receiverFirstName = customerFunctionsService.getCustomerFirstNameById(receiverAccount.getCustomerId());
														if(receiverFirstName.equalsIgnoreCase(getFirstName)) {
															if(customerFunctionsService.transfer(senderBankId, receiverBankId, amount5, "pending", "external") > 0) {									
																customerFunctionsService.withdrawal(accountNumber5, amount5);
																log.info("Transfer has posted. Please check Transactin Center for details");
															}
															break;
														} else {
															log.error("The enter first name " + getFirstName + " is not matched with account number " + receiverAccountNumber);
															continue;
														}		
													} catch (BusinessException e) {
														log.warn(e.getMessage());
													}
												} else if (s.equalsIgnoreCase("n")) {
													log.info("Cancel transaction...");
													break;
												} else {
													log.error("Invalid input value. Please try again...");
													continue;
												}
												break;
											} while(true);												
										}
										break;
									} while(true);
								}
							} else {
								log.error("Sorry. The receiver account number: " + receiverAccountNumber + " is not inefficiency");
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
					}			
					break;
				} while(true);
				break;
			case 6:
				log.info("Welcome to Transaction Center. Loading transaction.....");
				log.info("=======================================================");
				try {					
					// Loading all customer's bank accounts
					List<BankAccount> bankAccountsList = customerFunctionsService.getActiveBankByID(customer.getId());
					List<Transaction> sendingTransactions = new ArrayList<>();
					List<Transaction> finishTransactions = new ArrayList<>();
					List<Transaction> acceptingTransactions = new ArrayList<>();
					if(bankAccountsList!=null && bankAccountsList.size()>0) {
						// Iterate over every transactions, classify them by sender and status				
						for(BankAccount b: bankAccountsList) {
							List<Transaction> transactionsList = customerFunctionsService.getTransactionsBySenderBankId(b.getId());
							if(transactionsList!=null && transactionsList.size()>0) {
								for(Transaction t: transactionsList) {
									if(t.getStatus().equalsIgnoreCase("pending")) {
										sendingTransactions.add(t);
									} else {
										finishTransactions.add(t);
									}
								}
							}
							transactionsList = customerFunctionsService.getTransactionsByReceiverBankId(b.getId());
							if(transactionsList!=null && transactionsList.size()>0) {
								for(Transaction t: transactionsList) {
									BankAccount senderBank = customerFunctionsService.getBankByBankID(t.getBankSenderId());
									BankAccount receiverBank = customerFunctionsService.getBankByBankID(t.getBankReceiverId());
									if(senderBank.getCustomerId()!= receiverBank.getCustomerId()) {
										if(t.getStatus().equalsIgnoreCase("pending")) {
											acceptingTransactions.add(t);
										} else {
											finishTransactions.add(t);
										}
									}	
								}
							}
						}
					}	
					
					if(finishTransactions!=null && finishTransactions.size()>0) {
						log.info("\nYou have " + finishTransactions.size() + " accepted transactions");
						log.info("------------------------------------");
						for(Transaction t: finishTransactions) {
							BankAccount receiverBank = customerFunctionsService.getBankByBankID(t.getBankReceiverId());
							BankAccount senderBank = customerFunctionsService.getBankByBankID(t.getBankSenderId());
							String senderName = customerFunctionsService.getCustomerFirstNameById(senderBank.getCustomerId());
							String receiverName = customerFunctionsService.getCustomerFirstNameById(receiverBank.getCustomerId());
							log.info(t.printInfo() + ", From: " + senderName + ", To: " + receiverName + "'s account_number=" + receiverBank.getAccountNumber());
							
						}
						
					}
					if(sendingTransactions!=null && sendingTransactions.size()>0) {
						log.info("\nYou have " + sendingTransactions.size() + " sending transaction that is still pending");
						log.info("------------------------------------");
						for(Transaction t: sendingTransactions) {			
							BankAccount receiverBank = customerFunctionsService.getBankByBankID(t.getBankReceiverId());
							BankAccount senderBank = customerFunctionsService.getBankByBankID(t.getBankSenderId());
							String senderName = customerFunctionsService.getCustomerFirstNameById(senderBank.getCustomerId());
							String receiverName = customerFunctionsService.getCustomerFirstNameById(receiverBank.getCustomerId());
							log.info(t.printInfo() + ", From: " + senderName + ", To: " + receiverName + "'s account_number=" + receiverBank.getAccountNumber());
							
						}		
					}
					
					if(acceptingTransactions!=null && acceptingTransactions.size()>0) {		
						log.info("\nYou have " + acceptingTransactions.size() + " receiving transaction that is still waiting for you to accept");
						log.info("------------------------------------");
						for(int i=0; i < acceptingTransactions.size(); i++) {
							BankAccount receiverBank = customerFunctionsService.getBankByBankID(acceptingTransactions.get(i).getBankReceiverId());
							BankAccount senderBank = customerFunctionsService.getBankByBankID(acceptingTransactions.get(i).getBankSenderId());
							String senderName = customerFunctionsService.getCustomerFirstNameById(senderBank.getCustomerId());
							String receiverName = customerFunctionsService.getCustomerFirstNameById(receiverBank.getCustomerId());
							log.info((i+1) + ") " + acceptingTransactions.get(i).printInfo() + ", From: " + senderName 
									+ ", To: " + receiverName + "'s account_number=" + receiverBank.getAccountNumber());
						}
						
						do {
							log.info("Do you want to accept a transaction? Type Y/y for yes, Type N/n for no");
							String accept = scanner.nextLine().trim();
							if(accept.equalsIgnoreCase("y")) {
								int ch6;
								log.info("Please enter order number of transaction to accept");					
								try {
									ch6 = Integer.parseInt(scanner.nextLine().trim());
								} catch (NumberFormatException e) {
									log.warn(e.getMessage() + " is Invalid.");
									continue;
								} 
								int trans_pos = (ch6-1);
								if(ch6<1 || ch6>acceptingTransactions.size()) {
									log.info("Invalid choice. Please enter a number from 1 to " + acceptingTransactions.size());
									continue;
								} else {
									Transaction selTransaction = acceptingTransactions.get(trans_pos);
									BankAccount receiverBank = customerFunctionsService.getBankByBankID(selTransaction.getBankReceiverId());
									int c = customerFunctionsService.acceptTransfer(selTransaction.getId());
									if (c>0) {
										double newBalance = customerFunctionsService.deposit(receiverBank.getAccountNumber(), selTransaction.getAmount());
										log.info("Congratuation! Your account= " + receiverBank.getAccountNumber() + " with new balance=" + newBalance);
									}				
								}			
							} else if(accept.equalsIgnoreCase("n")){
								break;
							} else {
								log.error("Invalid type. Please try again");
								continue;
							}
							break;
						}while(true);
						
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 7:
				log.info("Thank you for using Bank Serivce. See you again");
				break;
			default:
				log.error("Invalid Choice!!!! Please enter choice between 1-3 only");
				break;
			}		
		} while(ch != 7);
	}
}
