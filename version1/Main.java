/* -----------
 * Author: Simikka Fung Sim Leung
 * Program Info: I used the OOP technique to create a simple online banking system.
 * -----------
 */

package version1;
import java.util.ArrayList;

import version1.Accounts.AccountType;

// import version1.BankingSystem;


public class Main {
	
	public static String retrieve_pw (ArrayList<Clients> all_clients, int cli_id) {
		for (Clients c: all_clients) {
			if (c.getClientID() == cli_id) {
				return c.getClientPW();
			}
		}
		return "";
	}
	
	public static void add_acc_to_cli (ArrayList<Clients> all_clients, Accounts new_acc, int cli_id) {
		for (Clients c: all_clients) {
			if (c.getClientID() == cli_id) {
				c.addAccount(new_acc.getAccountID());
			}
		}
	}
		
	public static int findAcc_id(ArrayList<Clients> all_clients, int cli_id, int acc_index) {
		int acc_id = -1;

		for (Clients c: all_clients) {
			if (c.getClientID() == cli_id) {
				if (acc_index >= c.getHow_many_acc() || acc_index < 0) {
					System.out.println("Transaction failed: The account index is out of range.");
				} else {
					acc_id = c.getAccID(acc_index);
				}
			}
		}
		return acc_id;
	}
	
	public static void withdraw_op (ArrayList<Clients> all_clients, ArrayList<Accounts> all_accounts, int cli_id, int acc_index, double amount) {
		// The acc_index variable starts from 0.
		int acc_id = findAcc_id(all_clients, cli_id, acc_index);
		if (acc_id != -1) {		//	If the account exists
			for (Accounts a: all_accounts) {
				if (a.getAccountID() == acc_id) {
					System.out.println("Before the withdrawal, the account has $" + a.getBalance());
					if (amount > a.getBalance()) {
						System.out.println("Transaction failed: The account has an insufficient balance.");
					} else {
						a.withdraw(amount);
						System.out.println("You have withdrawn $" + amount + " successfully.");
						System.out.println(a);
					}
				}
			}
		}
	}
	
	public static void deposit_op (ArrayList<Clients> all_clients, ArrayList<Accounts> all_accounts, int cli_id, int acc_index, double amount) {
		// The acc_index variable starts from 0.
		int acc_id = findAcc_id(all_clients, cli_id, acc_index);
		if (acc_id != -1) {		//	If the account exists
			for (Clients c: all_clients) {
				if (c.getClientID() == cli_id) {
					if (acc_index >= c.getHow_many_acc() || acc_index < 0) {
						System.out.println("Transaction failed: The account index is out of range.");
					} else {
						acc_id = c.getAccID(acc_index);
					}
				}
			}
		}
		
		for (Accounts a: all_accounts) {
			if (a.getAccountID() == acc_id) {
				System.out.println("Before the deposit, the account has $" + a.getBalance());
				a.deposit(amount);
				System.out.println("You have deposited $" + amount +" successfully.");
				System.out.println(a);
			}
		}
		
	}
	
	public static void transfer_op (ArrayList<Clients> all_clients, ArrayList<Accounts> all_accounts, int from_cli_id, int from_acc_index, int to_acc_id, double amount) {
		boolean transfer_successful = false;
		int acc_id = findAcc_id(all_clients, from_cli_id, from_acc_index);
		
		if (acc_id != -1) {		//	If the account exists
			for (Accounts from_a: all_accounts) {
				if (from_a.getAccountID() == acc_id) {
					System.out.println("Before the transfer, the account of the sender has $" + from_a.getBalance());
					if (amount > from_a.getBalance()) {
						System.out.println("Transaction failed: The sender's account has an insufficient balance.");
					} else {
						for (Accounts to_a: all_accounts) {
							if (to_a.getAccountID() == to_acc_id) {
								from_a.transfer_from(to_a, amount);
								to_a.transfer_to(from_a, amount);
								transfer_successful = true;
							}
						}
						
						if (transfer_successful == false) {
							System.out.println("Transaction failed: The recipient is not found.");
						} else {
							System.out.println("You have transferred $" + amount + " to account " + to_acc_id + " successfully.");
							System.out.println(from_a);
						}

					}
				}
			}
		}		
	}
		
	public static void main (String[] args) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Welcome to the online banking system built by Simikka Leung.");
		System.out.println("----------------------------------------------------------------");
		System.out.println();
		
		// Task 1
		System.out.println("Task 1: Create new clients.");
		ArrayList<Clients> all_clients = new ArrayList<Clients>();
		all_clients.add(new Clients("Tom", 1, "tom123"));
		all_clients.add(new Clients("Anna", 2, "anna123"));
		all_clients.add(new Clients("John", 3, "john123"));
		
		System.out.println(all_clients);
		System.out.println();
		
		// Task 2
		System.out.println("Task 2: Retrieve the password of the client 1.");
		System.out.println(retrieve_pw(all_clients, 1));
		System.out.println();
		
		// Task 3
		System.out.println("Task 3: Create new accounts and aassociate the account ids with clients.");
		ArrayList<Accounts> all_accounts = new ArrayList<Accounts>();
		
		Accounts new_acc = new Accounts(1000, AccountType.checking);
		all_accounts.add(new_acc);
		add_acc_to_cli(all_clients,new_acc,2);

		
		new_acc = new Accounts(500, AccountType.checking);
		all_accounts.add(new_acc);
		
		add_acc_to_cli(all_clients,new_acc,1);

		
		new_acc = new Accounts(2000, AccountType.checking);
		all_accounts.add(new_acc);
		
		add_acc_to_cli(all_clients,new_acc,3);


		new_acc = new Accounts(30000, AccountType.saving);
		all_accounts.add(new_acc);
		
		add_acc_to_cli(all_clients,new_acc,2);

		
		System.out.println(all_clients);
		System.out.println();
		
		//Task 4
		System.out.println("Task 4: Tom wants to withdraw money from his first account.");
		withdraw_op (all_clients, all_accounts, 1, 0, 100);
		System.out.println();
		
		//Task 5
		System.out.println("Task 5: Anna wants to deposit money into her second account.");
		deposit_op (all_clients, all_accounts, 2, 1, 1000);
		System.out.println();
		
		//Task 6
		System.out.println("Task 6: Anna wants to send money to Tom.");
		transfer_op (all_clients, all_accounts, 2, 1, 2, 888);
		System.out.println();
		
		//Task 7
		System.out.println("Task 7: Print the history of all accounts.");
		System.out.println(all_accounts);
		System.out.println();
	}

}
