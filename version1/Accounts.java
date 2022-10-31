/* -----------
 * Author: Simikka Fung Sim Leung
 * Program Info: I used the OOP technique to create a simple online banking system.
 * -----------
 */

package version1;
import java.time.*;

// import java.util.Vector;

public class Accounts{

	private static int number_of_accounts = 1;
	private int accountID;
	private double balance;
	private String history = "";
	// private Vector<String> history_vec;
	
	enum AccountType {
		  checking,
		  saving
		}
	
	public AccountType type;
	
	// Default Constructor
	public Accounts() {
		balance = 0;
		accountID = number_of_accounts;
		number_of_accounts++;
		type = AccountType.checking;
	}
	
	// Normal Constructors
	public Accounts(double initial_balance) {
		balance = initial_balance;
		accountID = number_of_accounts;
		number_of_accounts++;
		type = AccountType.checking;
		LocalDateTime present = LocalDateTime.now();
		history = history + "The account was created on " + present + " and its initial balance was $" + initial_balance + ".\n";
	}
	
	public Accounts(AccountType type) {
		balance = 0;
		accountID = number_of_accounts;
		number_of_accounts++;
		this.type = type;
	}
	
	public Accounts(double initial_balance, AccountType type) {
		balance = initial_balance;
		accountID = number_of_accounts;
		number_of_accounts++;
		this.type = type;
		LocalDateTime present = LocalDateTime.now();
		history = history + "The account was created on " + present + " and its initial balance was $" + initial_balance + ".\n";
	}
	
	
	// Getters and Setters
	public int getAccountID() {
		return accountID;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public AccountType getType() {
		return type;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setType(AccountType type) {
		this.type = type;
	}
	
	
	public void deposit (double amount) {
		balance += amount;
		LocalDateTime present = LocalDateTime.now();
		history = history + "$" + amount+ " was deposited on " + present + ".\n";
		
	}
	
	public void withdraw (double amount) {
		if (balance >= amount) {
			balance -= amount;
			LocalDateTime present = LocalDateTime.now();
			history = history + "$" + amount+ " was withdrawn on " + present + ".\n";
		}
	}
	
	public void transfer_from (Accounts to_a, double amount) {
		if (balance >= amount) {
			balance -= amount;	
			LocalDateTime present = LocalDateTime.now();
			history = history + "$" + amount+ " was sent to account " + to_a.getAccountID() + " on " + present + ".\n";
		}
	}
	
	public void transfer_to (Accounts from_a, double amount) {
		balance += amount;
		LocalDateTime present = LocalDateTime.now();
		history = history + "$" + amount+ " was deposited by account " + from_a.getAccountID() + " on " + present + ".\n";

	}
	
	// To return the transaction history
	public String toString() {
		String summary = "\nThe history of account " + accountID + ":\n"+ history + "The current balance is $" + balance + ".\n";
		return summary;
	}
	
}
