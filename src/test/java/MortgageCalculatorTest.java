import org.java.mateus.MortgageCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MortgageCalculatorTest {

    @Test
    void testCalculateMonthlyInterest() {
        float result = MortgageCalculator.calculateMonthlyInterest(12.0F);
        Assertions.assertEquals(0.01F, result, 0.00001);
    }

    @Test
    void testCalculateMonthsOfPayment() {
        int result = MortgageCalculator.calculateMonthsOfPayment(10);
        Assertions.assertEquals(120, result);
    }

    @Test
    void testCalculateCoefficient() {
        float monthlyInterest = 0.01F;
        int months = 12;
        double expected = Math.pow(1.01, 12);
        double result = MortgageCalculator.calculateCoefficient(monthlyInterest, months);
        Assertions.assertEquals(expected, result, 0.0001);
    }

    @Test
    void testCalculateCoefficientRatio() {
        float monthlyInterest = 0.01F;
        double coefficient = Math.pow(1.01, 12);
        double expected = (monthlyInterest * coefficient) / (coefficient - 1);
        double result = MortgageCalculator.calculateCoefficientRatio(monthlyInterest, coefficient);
        Assertions.assertEquals(expected, result, 0.0001);
    }

    @Test
    void testCalculateMonthlyPayment() {
        double principal = 100000;
        double coefficientRatio = 0.01;
        double result = MortgageCalculator.calculateMonthlyPayment(principal, coefficientRatio);
        Assertions.assertEquals(1000.0, result, 0.001);
    }

    @Test
    void testFormatMortgagePayment() {
        String result = MortgageCalculator.formatMortgagePayment(1234.56);
        Assertions.assertTrue(result.contains("1,234.56") || result.contains("1.234,56")); // Locale-dependent
    }

    @Test
    void testCalculateRemainingLoanBalance() {
        double principal = 100000;
        int monthsPaid = 12;
        float monthlyInterest = 0.01F;
        int totalMonths = 24;
        double result = MortgageCalculator.calculateRemainingLoanBalance(principal, monthsPaid, monthlyInterest, totalMonths);
        Assertions.assertTrue(result > 0 && result < principal);
    }
}
