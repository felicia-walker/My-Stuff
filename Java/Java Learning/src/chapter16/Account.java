package chapter16;

// Defines a customer account
public class Account {
  // Constructor
  public Account(int accountNumber, int balance) {
    this.accountNumber = accountNumber;            // Set the account number
    this.balance = balance;                        // Set the initial balance
  }

  // Return the current balance
  public int getBalance() {
    return balance;  
  }

  // Set the current balance
  public void setBalance(int balance) {
    this.balance = balance;  
  }

  public int getAccountNumber() {
    return accountNumber;  
  }

  public String toString() {
    return "A//C No. "+accountNumber+" : $"+balance;
  }
  
  public int getNumCredits() {
	  return nCredits;
  }
  
  public int getNumDebits() {
	  return nDebits;
  }

  public void incrCredits() {
	  nCredits++;
  }
  
  public void incrDebits() {
	  nDebits++;
  }
  
  private int balance;                             // The current account balance
  private int accountNumber;                       // Identifies this account
  private int nCredits = 0;
  private int nDebits = 0;
}
