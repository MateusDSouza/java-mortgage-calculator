package org.java.mateus;

import java.text.NumberFormat;

public class MortgageReport {
    final static Console console = new Console();
    final static MortgageCalculator mortgageCalculator = new MortgageCalculator(console.getPrincipal(), console.getAnnualInterestRate(), console.getYearsOfPayment());

    public static String formatMortgagePayment(double value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    public static void outputMortgageDetails() {
        printHeader("MORTGAGE PAYMENT");
        printMortgagePayment();
        printHeader("PAYMENT SCHEDULE");
        printPaymentSchedule();
    }

    private static void printHeader(String title) {
        System.out.println("\n" + title + "\n" + "-".repeat(title.length()));
    }

    private static void printMortgagePayment() {
        System.out.println(formatMortgagePayment(mortgageCalculator.monthlyPayment));
    }

    private static void printPaymentSchedule() {
        for (int month = 1; month <= mortgageCalculator.getMonthsOfPayment(); month++) {
            double remainingBalance = mortgageCalculator.calculateRemainingLoanBalance(month);
            System.out.printf("Loan Balance after %d months: %s%n",
                    month, formatMortgagePayment(remainingBalance));
        }
    }
}
