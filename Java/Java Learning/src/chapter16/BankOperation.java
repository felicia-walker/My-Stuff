package chapter16;

import java.util.Random;

public class BankOperation {
	
	private final static int NUM_CLERKS = 10;
	private static int nCredits = 0;
	private static int nDebits = 0;
	
  public static void main(String[] args) {
    int[] initialBalance = {500, 800};                 // The initial account balances
    int[] totalCredits = new int[initialBalance.length]; //Two different cr totals
    int[] totalDebits = new int[initialBalance.length];  //Two different db totals
    int transactionCount = 20;                         // Number of debits and credits

    // Create the bank and the clerks...
    Bank theBank = new Bank();                         // Create a bank
    Clerk[] clerks = new Clerk[NUM_CLERKS];
    Thread[] clerkThreads = new Thread[NUM_CLERKS];
    for (int i = 0 ; i < NUM_CLERKS; i++) {
    	clerks[i] = new Clerk(theBank, i);                 // Create the first clerk
    	
        // Create the threads for the clerks as daemon, and start them off
        clerkThreads[i] = new Thread(clerks[i]);
        clerkThreads[i].setDaemon(true);                      // Set first as daemon
        clerkThreads[i].start();                              // Start the first
    }

    // Create the accounts, and initialize total credits and debits
    Account[] accounts = new Account[initialBalance.length]; 
    for(int i = 0; i < initialBalance.length; i++) {
      accounts[i] = new Account(i+1, initialBalance[i]); // Create accounts
      totalCredits[i] = totalDebits[i] = 0;
    }

    // Create transactions randomly distributed between the accounts
    Random rand = new Random();
    Transaction transaction;                         // Stores a transaction
    int amount = 0;                                  // Stores an amount of money
    int select = 0;                                  // Selects an account
    for(int i = 1; i <= transactionCount; i++) {
	      // Choose an account at random for credit operation
	      select = rand.nextInt(accounts.length);
	      
	      int tmpTransaction = randTransaction();
	      int j = rand.nextInt(NUM_CLERKS);
	      if (tmpTransaction == Transaction.CREDIT) {
	    	  clerkThreads[j].setPriority(Thread.MAX_PRIORITY);     // Credits are a low priority
		      amount = 50 + rand.nextInt(26);                // Generate amount of $50 to $75
		      totalCredits[select] += amount;                // Keep total credit tally
	      } else {
	    	  clerkThreads[j].setPriority(Thread.MIN_PRIORITY);     // Credits are a low priority	    	  
		      amount = 30 + rand.nextInt(31);                // Generate amount of $50 to $75
		      totalDebits[select] += amount;                // Keep total credit tally
	      }
	      
	      transaction = new Transaction(accounts[select],       // Account
	                                        tmpTransaction, // Credit transaction
	                                        amount);            //  of amount
	
	      clerks[j].doTransaction(transaction);
    }
    
    // Wait until both clerks are done
    for (int i = 0; i < NUM_CLERKS; i++) {
    	clerks[i].isBusy();
    }

    // Now output the results
    for(int i = 0; i < accounts.length; i++) {   
      System.out.println("Account Number:"+accounts[i].getAccountNumber()+"\n"+
         "Original balance    : $" + initialBalance[i] + "\n" +
         "Total credits       : $" + totalCredits[i] + "\n" +
         "Total debits        : $" + totalDebits[i] + "\n" +
         "Number of credits   : " + accounts[i].getNumCredits() + "\n" +
         "Number of debits    : " + accounts[i].getNumDebits() + "\n" +
         "Final balance       : $" + accounts[i].getBalance() + "\n" +
         "Should be           : $" + (initialBalance[i] 
                                   + totalCredits[i]
                                   - totalDebits[i]) + "\n");
    }
  }
  static 
  private int randTransaction() {
	  Random rand = new Random();
	  if (rand.nextInt(50) < 25) {
		  return Transaction.CREDIT;
	  } else {
		  return Transaction.DEBIT;
	  }
  }
}
