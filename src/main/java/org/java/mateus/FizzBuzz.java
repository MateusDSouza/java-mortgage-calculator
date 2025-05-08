package org.java.mateus;

import java.util.Scanner;

public class FizzBuzz {
    public static void main(String[] args) {

        final int FIZZ_NUMBER = 5;
        final int BUZZ_NUMBER = 3;
        final int FIZZBUZZ_NUMBER = FIZZ_NUMBER*BUZZ_NUMBER;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Number?");

        double fizzbuzzEvalNumber = scanner.nextDouble();

        if (fizzbuzzEvalNumber % FIZZBUZZ_NUMBER == 0) {
            System.out.println("FizzBuzz");
        }
        else if (fizzbuzzEvalNumber % FIZZ_NUMBER == 0) {
            System.out.println("Fizz");
        }
        else if (fizzbuzzEvalNumber % BUZZ_NUMBER == 0) {
            System.out.println("Buzz");
        }
        else
            System.out.println(fizzbuzzEvalNumber);
    }
}
