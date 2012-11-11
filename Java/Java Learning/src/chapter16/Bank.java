package chapter16;

// Define the bank
class Bank {
  // Perform a transaction
   public void doTransaction(Transaction transaction) {
    switch(transaction.getTransactionType()) {
      case Transaction.CREDIT:
        synchronized(transaction.getAccount()) {
          System.out.println("Start credit of " +
                  transaction.getAccount() + " amount: " + 
                  transaction.getAmount());
 
          // Get current balance
          int balance = transaction.getAccount().getBalance(); 

          // Credits require a lot of checks...
          try {
            Thread.sleep(100);
          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance += transaction.getAmount();           // Increment the balance
          transaction.getAccount().setBalance(balance); // Restore account balance
          transaction.getAccount().incrCredits();
          System.out.println("  End credit of " +
                  transaction.getAccount() + " amount: " + 
                  transaction.getAmount());
          transaction.getAccount().setBalance(balance); // Restore the account balance
          break;
        }
      case Transaction.DEBIT:
        synchronized(transaction.getAccount()) {
          System.out.println("Start debit of " +
                  transaction.getAccount() + " amount: " + 
                  transaction.getAmount());
 
          // Get current balance
          int balance = transaction.getAccount().getBalance(); 

          // Debits require even more checks...
          try {
            Thread.sleep(150);
          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance -= transaction.getAmount();          // Decrement the balance
          transaction.getAccount().setBalance(balance); // Restore account balance
          transaction.getAccount().incrDebits();
          System.out.println("  End debit of " +
                  transaction.getAccount() + " amount: " + 
                  transaction.getAmount());
          transaction.getAccount().setBalance(balance); // Restore the account balance
          break;
        }  

      default:                                          // We should never get here
        System.out.println("Invalid transaction");
        System.exit(1);
   }
  }
}
