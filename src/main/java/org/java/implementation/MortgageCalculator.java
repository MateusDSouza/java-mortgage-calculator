package org.java.mateus;

import java.text.NumberFormat;

public class MortgageCalculator {
    static final int MONTHS_IN_YEAR = 12;
    static final int PERCENT = 100;
    private double principal;
    private float annualInterestRate;
    private int yearsOfPayment;
    private float monthlyInterest;
    private int monthsOfPayment;
    public double monthlyPayment;

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

    int getMonthsOfPayment() {
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

    private double calculateCoefficient(int monthsOfPaymentsDone) {
        return Math.pow(1 + monthlyInterest, monthsOfPaymentsDone);
    }


    private double calculateCoefficientRatio() {
        return (monthlyInterest * calculateCoefficient(monthsOfPayment)) / (calculateCoefficient(monthsOfPayment) - 1);
    }

    private void calculateMonthlyPayment() {
        setMonthlyPayment(principal *
                calculateCoefficientRatio());
    }

    double calculateRemainingLoanBalance(int monthsOfPaymentsDone) {
        return principal * ((calculateCoefficient(monthsOfPayment) - calculateCoefficient(monthsOfPaymentsDone)) / (calculateCoefficient(monthsOfPayment) - 1));
    }


}
