package org.java.mateus;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Global variables
        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;
        final double LOWER_LIMIT = 1000;
        final double UPPER_LIMIT = 1000000;
        final float TAE_MIN = 0.0F;
        final float TAE_MAX = 30.0F;
        final int YEARS_OF_PAYMENT_MIN = 1;
        final int YEARS_OF_PAYMENT_MAX = 40;

        final String PRINCIPAL_STRING = String.format("Principal - From %s$ to %s$:", LOWER_LIMIT, UPPER_LIMIT);
        final String TAE_STRING = String.format("TAE - From %s%% to %s%%:", TAE_MIN, TAE_MAX);
        final String YEARS_OF_PAYMENT_STRING = String.format("Years of payment - From %s year to %s years:", YEARS_OF_PAYMENT_MIN, YEARS_OF_PAYMENT_MAX);


        // Scanner - read inputs
        Scanner scanner = new Scanner(System.in);

        // Local variables
        double principal;
        float annualInterestRate;
        int yearsOfPayment;

        // Input validation
        do {
            System.out.print(PRINCIPAL_STRING);
            principal = scanner.nextDouble();
        } while ((principal > UPPER_LIMIT) || (principal < LOWER_LIMIT));


         do {
            System.out.print(TAE_STRING);
            annualInterestRate = scanner.nextFloat();
        } while ((annualInterestRate <= TAE_MIN) || (annualInterestRate >= TAE_MAX));

         do {
            System.out.print("Years of payment - From 1 year to 40 years:");
            yearsOfPayment = scanner.nextInt();
        } while (yearsOfPayment < YEARS_OF_PAYMENT_MIN || yearsOfPayment > YEARS_OF_PAYMENT_MAX);

         // Calculated auxiliary variables
        float monthlyInterest = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int monthsOfPayment = yearsOfPayment * MONTHS_IN_YEAR;
        double coefficient = Math.pow(1 + monthlyInterest, monthsOfPayment);
        double coefficientRationalized = (monthlyInterest * coefficient) / (coefficient - 1);

        // Mortgage calculation

        double monthlyPayment = principal *
                coefficientRationalized;

        // Formatted mortgage monthly payment
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(monthlyPayment);

        // Output result
        System.out.println("That's your monthly payment: \n" + mortgageFormatted);
    }
    }