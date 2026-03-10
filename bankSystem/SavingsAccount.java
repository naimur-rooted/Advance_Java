package com.mycompany.banksystem;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount() {}

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public double getInterestRate() { return interestRate; }
}
