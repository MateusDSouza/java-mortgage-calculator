package org.java.mateus;

import java.util.Scanner;

public class Console {
    static final Scanner scanner = new Scanner(System.in);
    final double LOWER_LIMIT = 1000;
    final double UPPER_LIMIT = 1000000;
    final float TAE_MIN = 0.0F;
    final float TAE_MAX = 30.0F;
    final int YEARS_OF_PAYMENT_MIN = 1;
    final int YEARS_OF_PAYMENT_MAX = 40;
    final String PRINCIPAL_STRING = String.format("Principal - From %s€ to %s€:", LOWER_LIMIT, UPPER_LIMIT);
    final String TAE_STRING = String.format("TAE - From %s%% to %s%%:", TAE_MIN, TAE_MAX);
    final String YEARS_OF_PAYMENT_STRING = String.format("Years of payment - From %s year to %s years:", YEARS_OF_PAYMENT_MIN, YEARS_OF_PAYMENT_MAX);
    private double principal;
    private float annualInterestRate;
    private int yearsOfPayment;

    public Console() {
        setPrincipal(inputValue(PRINCIPAL_STRING, LOWER_LIMIT, UPPER_LIMIT));
        setAnnualInterestRate((float) inputValue(TAE_STRING, TAE_MIN, TAE_MAX));
        setYearsOfPayment((int) Console.inputValue(YEARS_OF_PAYMENT_STRING, YEARS_OF_PAYMENT_MIN, YEARS_OF_PAYMENT_MAX));
    }

    private static double inputValue(String message, double lowerLimit, double upperLimit) {
        double value;
        do {
            System.out.print(message);
            value = scanner.nextDouble();
        } while ((value < lowerLimit) || (value > upperLimit));
        return value;
    }

    public double getPrincipal() {
        return principal;
    }

    private void setPrincipal(double principal) {
        this.principal = principal;
    }

    public float getAnnualInterestRate() {
        return annualInterestRate;
    }

    private void setAnnualInterestRate(float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getYearsOfPayment() {
        return yearsOfPayment;
    }

    private void setYearsOfPayment(int yearsOfPayment) {
        this.yearsOfPayment = yearsOfPayment;
    }
}
