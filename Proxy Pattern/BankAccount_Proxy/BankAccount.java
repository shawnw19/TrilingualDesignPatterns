package BankAccount_Proxy;

public class BankAccount {
    private String owner;//will be directed assigned value to ensure uniqueness of bank owner
    private double balance;

    public BankAccount(String name) {
        owner = name;
        balance=0;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -=amount;
    }

    public double getBalance() {
        return balance;
    }

}
