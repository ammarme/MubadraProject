package com.ammar.mubadraproject.model;

import java.io.Serializable;

public class AmortizationItem implements Serializable {
    private int month;
    private double beginningBalance, interest, principal, endingBalance;

    public AmortizationItem(int month, double beginningBalance, double interest, double principal, double endingBalance) {
        this.month = month;
        this.beginningBalance = beginningBalance;
        this.interest = interest;
        this.principal = principal;
        this.endingBalance = endingBalance;
    }

    public int getMonth() {
        return month;
    }

    public double getBeginningBalance() {
        return beginningBalance;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getEndingBalance() {
        return endingBalance;
    }
}
