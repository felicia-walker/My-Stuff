package chapter16;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class Clerk implements Runnable {
  // Constructor
  public Clerk(Bank theBank, int clerknum) {
    this.theBank = theBank;           // Who the clerk works for
    this.clerknum = clerknum;
  }

  // Receive a transaction
  synchronized public void doTransaction(Transaction transaction) {
    while(inTray.size() >= maxTransactions) {
      try {
        wait();

      } catch(InterruptedException e) {
        System.out.println(e);
      }
    }

    inTray.add(transaction);           // Add transaction to the list
    notifyAll();
  }

  // The working clerk...
  synchronized public void run() {
    while(true) {
      while(inTray.size() == 0) {      // No transaction waiting?
        try {
          wait();                      // Then take a break until there is
        } catch(InterruptedException e) {
          System.out.println(e);
        }
      }
      System.out.println("Clerk " + clerknum);
      theBank.doTransaction(inTray.remove(0));
        notifyAll();                   // Notify other threads locked on this clerk
    }
  }

  // Busy check
  synchronized public void isBusy() {
    while(inTray.size() != 0) {        // Is this object busy?
      try {
        wait();                        // Yes, so wait for notify call
      } catch(InterruptedException e) {
        System.out.println(e);
      }
      return;                          // It is free now
    }
  }

  private Bank theBank;
  
  private List<Transaction> inTray =   // The in-tray holding transactions
                   Collections.synchronizedList(new LinkedList<Transaction>());

  private int maxTransactions = 8;     // Maximum transactions in the in-tray
  private int clerknum = 0;
}
