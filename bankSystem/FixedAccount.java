package com.mycompany.banksystem;

public class FixedAccount extends Account {
    private int tenureYear;

    public FixedAccount() {}

    public void setTenureYear(int tenureYear) {
        this.tenureYear = tenureYear;
    }
    public int getTenureYear() { return tenureYear; }
}
