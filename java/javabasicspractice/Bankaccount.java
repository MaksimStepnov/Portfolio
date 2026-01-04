package bankaccount;

public class BankAccount {
    public static void main(String[] args) {
        //Create bank account
        BankAccount account1 = new BankAccount("Maksim");
        BankAccount account2 = new BankAccount("Stepnov");

        //deposit
        account1.deposit(1000);
        account2.deposit(500);

        //withdraw
        account1.withdraw(200);

        //transfer
        account1.transfer(300, account2);

        // apply interest
        account1.applyInterest();

        //toString
        System.out.println(account1.toString());
        System.out.println(account2.toString());

        //find
        BankAccount[] accounts = {account1, account2};
        BankAccount found = BankAccount.find(accounts, "Maksim");
        System.out.println("Found: " + found.toString());
    }

    private String name;
    private double balance;
    private int account_number;
    private static int num_accounts = 0;
    private static double interest_rate = 0.02;
    private static int next_account_number = 1000;

    public BankAccount(String name) {
        this.name = name;
        this.balance = 0.0;
        this.account_number = next_account_number;
        next_account_number++;
        num_accounts++;
    }

    //Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    //Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
        }
    }

    //Transfer
    public boolean transfer(double amount, BankAccount recipient) {
        if (amount > 0 && amount <= balance && recipient != null) {
            this.balance = this.balance - amount;
            recipient.balance = recipient.balance + amount;
            return true;
        }
        return false;
    }

    //interest
    public void applyInterest() {
        double interest = balance * interest_rate;
        balance = balance + interest;
    }

    //Accessor
    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    //toString
    public String toString() {
        return "Account Number: " + account_number + 
               ", Name: " + name + 
               ", Balance: $" + balance;
    }

    //find account
    public static BankAccount find(BankAccount[] accounts, String name) {
        if (accounts == null) {
            return null;
        }
        if (name == null) {
            return null;
        }
        
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getName().equals(name)) {
                    return accounts[i];
                }
            }
        }
        return null;
    }
}