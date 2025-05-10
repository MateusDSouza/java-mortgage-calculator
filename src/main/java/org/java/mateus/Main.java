package org.java.mateus;

public class Main {

    public static void main(String[] args) {

        final Console console = new Console();

        final MortgageCalculator mortgageCalculator = new MortgageCalculator(console.getPrincipal(), console.getAnnualInterestRate(), console.getYearsOfPayment());
        mortgageCalculator.outputMortgageDetails();
    }

}