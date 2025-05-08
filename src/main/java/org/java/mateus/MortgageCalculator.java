package org.java.mateus;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * The MortgageCalculator class provides methods to compute mortgage-related
 * calculations such as monthly payments, loan balances, and amortization schedules.
 * It takes into account principal, interest rate, and loan term.
 */
public class MortgageCalculator {

    // Global variables and objects
    static final int MONTHS_IN_YEAR = 12;
    static final int PERCENT = 100;
    static final double LOWER_LIMIT = 1000;
    static final double UPPER_LIMIT = 1000000;
    static final float TAE_MIN = 0.0F;
    static final float TAE_MAX = 30.0F;
    static final int YEARS_OF_PAYMENT_MIN = 1;
    static final int YEARS_OF_PAYMENT_MAX = 40;

    /**
     * Calculates the monthly interest rate from the annual interest rate.
     *
     * @param annualInterestRate The annual percentage rate (APR).
     * @return The monthly interest rate as a float.
     */
    public static float calculateMonthlyInterest(float annualInterestRate) {
        return annualInterestRate / PERCENT / MONTHS_IN_YEAR;
    }

    /**
     * Converts loan term from years to number of monthly payments.
     *
     * @param yearsOfPayment The total loan term in years.
     * @return The total number of monthly payments.
     */
    public static int calculateMonthsOfPayment(int yearsOfPayment) {
        return yearsOfPayment * MONTHS_IN_YEAR;
    }

    /**
     * Computes the compound coefficient used in mortgage calculation.
     *
     * @param monthlyInterest The monthly interest rate.
     * @param monthsOfPayment The total number of payments.
     * @return The compounded coefficient value.
     */
    public static double calculateCoefficient(float monthlyInterest, int monthsOfPayment ) {
        return Math.pow(1 + monthlyInterest, monthsOfPayment);
    }

    /**
     * Computes the coefficient ratio for amortization calculation.
     *
     * @param monthlyInterest The monthly interest rate.
     * @param coefficient The compounded coefficient.
     * @return The ratio used in the mortgage formula.
     */
    public static double calculateCoefficientRatio(float monthlyInterest,double coefficient) {
        return (monthlyInterest * coefficient) / (coefficient - 1);
    }

    /**
     * Calculates the monthly mortgage payment.
     *
     * @param principal The total loan amount.
     * @param coefficientRatio The amortization coefficient ratio.
     * @return The monthly payment amount.
     */
    public static double calculateMonthlyPayment(double principal,double coefficientRatio) {
        return principal *
                coefficientRatio;
    }

    /**
     * Formats a numeric payment value as a currency string.
     *
     * @param monthlyPayment The numeric monthly payment.
     * @return A string formatted as currency.
     */
    public static String formatMortgagePayment(double monthlyPayment) {
        return NumberFormat.getCurrencyInstance().format(monthlyPayment);
    }

    /**
     * Prints the formatted monthly payment slip.
     *
     * @param formattedMonthlyPayment The payment formatted as currency.
     */
    public static void outputPaymentSlip(String formattedMonthlyPayment) {
        System.out.println("\nMORTGAGE PAYMENT\n---------------- \n" + formattedMonthlyPayment);
    }

    /**
     * Calculates the remaining loan balance after a certain number of payments.
     *
     * @param principal The initial loan amount.
     * @param monthsOfPaymentsDone Number of payments already made.
     * @param monthlyInterest Monthly interest rate.
     * @param monthsOfPayment Total number of monthly payments.
     * @return The remaining balance on the loan.
     */
    public static double calculateRemainingLoanBalance (double principal, int monthsOfPaymentsDone,float monthlyInterest, int monthsOfPayment) {
        return principal*((calculateCoefficient(monthlyInterest,monthsOfPayment) - calculateCoefficient(monthlyInterest,monthsOfPaymentsDone))/(calculateCoefficient(monthlyInterest,monthsOfPayment) -1));
    }

    /**
     * Outputs the full payment schedule, showing remaining loan balance for each month.
     *
     * @param yearsOfPayment Loan duration in years.
     * @param principal The initial loan amount.
     * @param monthlyInterest The monthly interest rate.
     * @param monthsOfPayment Total number of payments.
     */
    public static void outputLoanBalance(int yearsOfPayment, double principal,float monthlyInterest, int monthsOfPayment) {
        System.out.println("\nPAYMENT SCHEDULE \n----------------");
        for (int p=1;p<=calculateMonthsOfPayment(yearsOfPayment);p++) {
            double remainingLoanBalance =
                    calculateRemainingLoanBalance(principal,p, monthlyInterest, monthsOfPayment);
            System.out.println("Loan Balance after "+ p + " months: "
                    +formatMortgagePayment(remainingLoanBalance));
        }
    }

    /**
     * Prompts the user for input until a valid number within range is provided.
     *
     * @param message Message to display to the user.
     * @param lowerLimit Minimum acceptable value.
     * @param upperLimit Maximum acceptable value.
     * @return A validated double value entered by the user.
     */
    public static double inputValue(String message, double lowerLimit, double upperLimit) {
        final Scanner scanner = new Scanner(System.in);
        double value;
        do {
            System.out.print(message);
            value = scanner.nextDouble();
        } while ((value < lowerLimit) || (value > upperLimit));
        return value;
    }

    /**
     * Main method that executes the mortgage calculation process:
     * input collection, computation, and output.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        //Communication with users
        final String PRINCIPAL_STRING = String.format("Principal - From %s€ to %s€:", LOWER_LIMIT, UPPER_LIMIT);
        final String TAE_STRING = String.format("TAE - From %s%% to %s%%:", TAE_MIN, TAE_MAX);
        final String YEARS_OF_PAYMENT_STRING = String.format("Years of payment - From %s year to %s years:", YEARS_OF_PAYMENT_MIN, YEARS_OF_PAYMENT_MAX);

        // Input validation
        double principal = inputValue(PRINCIPAL_STRING, LOWER_LIMIT, UPPER_LIMIT);
        float annualInterestRate = ((float) inputValue(TAE_STRING, TAE_MIN, TAE_MAX));
        int yearsOfPayment = (int) inputValue(YEARS_OF_PAYMENT_STRING, YEARS_OF_PAYMENT_MIN, YEARS_OF_PAYMENT_MAX);

         // Calculated auxiliary variables
        float monthlyInterest = calculateMonthlyInterest(annualInterestRate);
        int monthsOfPayment = calculateMonthsOfPayment(yearsOfPayment);
        double coefficient = calculateCoefficient(monthlyInterest,monthsOfPayment);
        double coefficientRatio = calculateCoefficientRatio(monthlyInterest,coefficient);

        // Mortgage calculation
        double monthlyPayment = calculateMonthlyPayment(principal,coefficientRatio);

        //Format payment
        String formattedMonthlyPayment = formatMortgagePayment(monthlyPayment);

        // Output payment slip
        outputPaymentSlip(formattedMonthlyPayment);

        // Loan Balance
        outputLoanBalance(yearsOfPayment,principal,monthlyInterest,monthsOfPayment);

    }
    }