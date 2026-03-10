package com.mycompany.banksystem;

public class Start {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount();
        sa.setAccountNumber("11111111");
        sa.setAccountHolderName("OOP1");
        sa.setBalance(10000);
        sa.setInterestRate(7.5);

        FixedAccount fa = new FixedAccount();
        fa.setAccountNumber("11111112");
        fa.setAccountHolderName("OOP1");
        fa.setBalance(100000);
        fa.setTenureYear(10);

        Bank b = new Bank();
        b.setName("DBBL");
        b.setSavingsAccount(sa);
        b.setFixedAccount(fa);

        // Print details
        System.out.println("Bank Name: " + b.getName());
        System.out.println("Savings Account Holder: " + b.getSavingsAccount().getAccountHolderName());
        System.out.println("Savings Account Number: " + b.getSavingsAccount().getAccountNumber());
        System.out.println("Savings Balance: " + b.getSavingsAccount().getBalance());
        System.out.println("Interest Rate: " + b.getSavingsAccount().getInterestRate());

        System.out.println("Fixed Account Holder: " + b.getFixedAccount().getAccountHolderName());
        System.out.println("Fixed Account Number: " + b.getFixedAccount().getAccountNumber());
        System.out.println("Fixed Balance: " + b.getFixedAccount().getBalance());
        System.out.println("Tenure Year: " + b.getFixedAccount().getTenureYear());
    }
}
