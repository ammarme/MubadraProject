package com.ammar.mubadraproject.model;

import android.util.Log;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Calculate the monthly payments if you take out a $principal mLoan for
 * the specified number of years at the specified tvInterest rate,
 * where interested is compounded monthly.
 * <p>
 * principal * rate
 * payment =  -------------------      where n = 12 * years,
 * 1  - (1 + r)^(-n)              r = (rate / 100) / 12
 * <p>
 * <p>
 * % java CarLoan 20000 5 6
 * Monthly payments = 386.6560305885655
 * Total tvInterest   = 3199.361835313932
 */
public class Loan implements Serializable {
    // Supplied Values:
    private double loanAmount, termInYears, interestRate;

    // Computed Values:
    private double monthlyPayment, totalLoanPayments, totalLoanInterest, totalCost, termInMonths;

    AmortizationItem[] amortizationItems;

    public Loan(double loanAmount, int termInYears, double annualInterestRate, double downPayment, double tradeInValue, double fees) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.interestRate = annualInterestRate;

        amortizationItems = new AmortizationItem[termInYears * 12];

        double taxableAmount = loanAmount - tradeInValue;
//        double salesTax = taxableAmount * salesTaxRate / 100D;
        double financedAmount = taxableAmount + fees - downPayment;

        monthlyPayment = calculateMonthlyPayment(financedAmount, termInYears, annualInterestRate);
        termInMonths = termInYears * 12;
        totalLoanPayments = monthlyPayment * termInMonths;
        totalLoanInterest = totalLoanPayments - financedAmount;
        totalCost = loanAmount + totalLoanInterest  + fees;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat interestFormat = NumberFormat.getPercentInstance();

        Log.d("Loan", "Loan Amount: " + currencyFormat.format(loanAmount));
        Log.d("Loan", "Loan Term: " + termInYears);
        Log.d("Loan", "Interest Rate: " + interestFormat.format(annualInterestRate));
        Log.d("Loan", "Monthly Payment: " + currencyFormat.format(monthlyPayment));
        Log.d("Loan", "Total Payments: " + currencyFormat.format(totalLoanPayments));
        Log.d("Loan", "Total Loan Interest: " + currencyFormat.format(totalLoanInterest));
        Log.d("Loan", "Total Cost: " + currencyFormat.format(totalCost));

        double principal = financedAmount;
        double monthlyInterestRate = annualInterestRate / 12D;

        for (int month = 1; month <= termInMonths; month++) {
            // Compute amount paid and new balance for each payment period
            double interestPaid = principal * (monthlyInterestRate / 100);
            double principalPaid = monthlyPayment - interestPaid;
            double newBalance = principal - principalPaid;

            // Output the data item
            Log.d("Loan",
                    String.format("%4d %12s %12s %12s %12s", month, currencyFormat.format(principal), currencyFormat.format(interestPaid),
                            currencyFormat.format(principalPaid), currencyFormat.format(newBalance)));

            AmortizationItem item = new AmortizationItem(month, principal, interestPaid, principalPaid, newBalance);
            amortizationItems[month - 1] = item;

            // Update the balance
            principal = newBalance;
        }
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getTermInYears() {
        return termInYears;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalLoanPayments() {
        return totalLoanPayments;
    }

    public double getTotalLoanInterest() {
        return totalLoanInterest;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTermInMonths() {
        return termInMonths;
    }

    public AmortizationItem[] getAmortizationItems() {
        return amortizationItems;
    }

    private double calculateMonthlyPayment(double financedAmount, int termInYears, double interestRate) {
        // Convert tvInterest rate into a decimal. eg. 3.75% ==> 0.0375
        interestRate /= 100D;

        // Monthly Interest Rate is the yearly rate divided by 12 months
        double monthlyRate = interestRate / 12D;

        // The term of the mLoan in months
        int termInMonths = termInYears * 12;

        // Calculate the monthly payment
        double monthlyPayment = (financedAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));

        return monthlyPayment;
    }
}
