package com.mycompany.banksystem;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account() {}

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public void depositMoney(double amount) {
        balance += amount;
    }
    public void withdrawMoney(double amount) {
        if (balance >= amount) balance -= amount;
        else System.out.println("Insufficient balance!");
    }
}
