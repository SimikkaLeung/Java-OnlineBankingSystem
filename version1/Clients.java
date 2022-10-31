/* -----------
 * Author: Simikka Fung Sim Leung
 * Program Info: I used the OOP technique to create a simple online banking system.
 * -----------
 */

package version1;

public class Clients {

	private static int number_of_clients = 0;
	private String clientName;
	private int clientID;
	private String clientPW;
	private int how_many_acc;
	private int[] acc;
	
	// Default Constructor
	public Clients() {
		number_of_clients++;		
		clientID = number_of_clients;
		clientName = "undefined";
		clientPW = "123456";
		how_many_acc = 0;
	}
	
	// Normal Constructors
	public Clients(String clientName) {
		number_of_clients++;		
		clientID = number_of_clients;
		this.clientName = clientName;
		clientPW = "123456";
		how_many_acc = 0;
	}
	
	public Clients(String clientName, String clientPW) {
		number_of_clients++;		
		clientID = number_of_clients;
		this.clientName = clientName;
		this.clientPW = clientPW;
		how_many_acc = 0;
	}
	
	public Clients(String clientName, int clientID, String clientPW) {
		number_of_clients++;		
		this.clientID = clientID;
		this.clientName = clientName;
		this.clientPW = clientPW;
		how_many_acc = 0;
	}
	
	// Getters and Setters	
	public String getClientName() {
		return clientName;
	}
	
	public int getClientID() {
		return clientID;
	}
	
	public String getClientPW() {
		return clientPW;
	}

		
	public int getHow_many_acc() {
		return how_many_acc;
	}
	
	public int getAccID(int index) {
		if (index<how_many_acc) {
			return acc[index];
		} else {
			return -1;
		}
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public void setClientPW(String clientPW) {
		this.clientPW = clientPW;
	}

	
	public void setHow_many_acc(int how_many_acc) {
		this.how_many_acc = how_many_acc;
	}
		
	// To add a new account
	public void addAccount(int accountID) {
		
		if (how_many_acc == 0) {
			acc = new int [1];
			acc[how_many_acc] = accountID;
		} else {
			expandAcc();
			acc[how_many_acc] = accountID;
		}
		how_many_acc++;
	}
	
	// Replace the old acc array with the new one.
	public void expandAcc() {
		if (how_many_acc > 0) {
			int[] temp = new int[how_many_acc+1];
			for (int i = 0; i < how_many_acc; i++) {
				temp[i] = acc[i];
			}
			acc = temp;
		}
	}
	
	// To return the client info
	public String toString() {
		String client_info;
		client_info = "\n" + clientID + " (" + clientName + ") has " + how_many_acc + " accounts: ";
		for (int i = 0; i < how_many_acc; i++) {
			client_info = client_info + acc[i] + "\t";
		}
		
		return client_info;
	}
}
