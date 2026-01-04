package oop;

/**
 *
 * @author MaksimPC
 */
public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance)
            balance = balance - amount;
    }

    public String toString() {
        return "Account: " + accountNumber + " , Balance: $" + balance;
    }

    public static void main(String[] args) {
        BankAccount b = new BankAccount("A001", 500);
        b.deposit(150);
        b.withdraw(200);
        System.out.println(b);
    }
    
}

