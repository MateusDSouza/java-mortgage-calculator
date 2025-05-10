package org.java.mateus;

import java.text.NumberFormat;

public class MortgageCalculator {
    static final int MONTHS_IN_YEAR = 12;
    static final int PERCENT = 100;
    //Initialized
    private double principal;
    private float annualInterestRate;
    private int yearsOfPayment;
    //Calculated
    private float monthlyInterest;
    private int monthsOfPayment;
    private double monthlyPayment;

    public MortgageCalculator(double principal, float annualInterestRate, int yearsOfPayment) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.yearsOfPayment = yearsOfPayment;
        this.calculateMonthsOfPayment();
        this.calculateMonthlyInterest();
        this.calculateMonthlyPayment();
    }

    private float getMonthlyInterest() {
        return monthlyInterest;
    }

    private void setMonthlyInterest(float monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    private double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    private float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    private double getMonthlyPayment() {
        return monthlyPayment;
    }

    private void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    private int getYearsOfPayment() {
        return yearsOfPayment;
    }

    public void setYearsOfPayment(int yearsOfPayment) {
        this.yearsOfPayment = yearsOfPayment;
    }

    private int getMonthsOfPayment() {
        return monthsOfPayment;
    }

    private void setMonthsOfPayment(int monthsOfPayment) {
        this.monthsOfPayment = monthsOfPayment;
    }

    private void calculateMonthlyInterest() {
        setMonthlyInterest(annualInterestRate / PERCENT / MONTHS_IN_YEAR);
    }


    private void calculateMonthsOfPayment() {
        setMonthsOfPayment(yearsOfPayment * MONTHS_IN_YEAR);
    }


    private double calculateCoefficient() {
        return Math.pow(1 + monthlyInterest, monthsOfPayment);
    }

    private double calculateCoefficient(int monthsOfPaymentsDone) {
        return Math.pow(1 + monthlyInterest, monthsOfPaymentsDone);
    }


    private double calculateCoefficientRatio() {
        return (monthlyInterest * calculateCoefficient()) / (calculateCoefficient() - 1);
    }

    private void calculateMonthlyPayment() {
        setMonthlyPayment(principal *
                calculateCoefficientRatio());
    }

    private String formatMortgagePayment(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    private double calculateRemainingLoanBalance(int monthsOfPaymentsDone) {
        return principal * ((calculateCoefficient() - calculateCoefficient(monthsOfPaymentsDone)) / (calculateCoefficient() - 1));
    }

    public void outputMortgageDetails() {
        System.out.println("\nMORTGAGE PAYMENT\n---------------- \n");
        System.out.println(formatMortgagePayment(monthlyPayment));
        System.out.println("\nPAYMENT SCHEDULE \n----------------");
        for (int p = 1; p <= getMonthsOfPayment(); p++) {
            double remainingLoanBalance =
                    calculateRemainingLoanBalance(p);
            System.out.println("Loan Balance after " + p + " months: "
                    + formatMortgagePayment(remainingLoanBalance));
        }
    }
}
