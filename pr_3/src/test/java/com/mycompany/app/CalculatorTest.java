package com.mycompany.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        double result = Calculator.add(1, 2);
        Assertions.assertEquals(3, result, "Додавання працює неправильно");
    }

    @Test
    public void testSubtract() {
        double result = Calculator.subtract(2, 1);
        Assertions.assertEquals(1, result, "Віднімання працює неправильно");
    }

    @Test
    public void testMultiply() {
        double result = Calculator.multiply(2, 2);
        Assertions.assertEquals(4, result, "Множення працює неправильно");
    }

    @Test
    public void testDivide() {
        double result = Calculator.divide(4, 2);
        Assertions.assertEquals(2, result, "Ділення працює неправильно");
    }

    @Test
    public void testDivideByZero() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Calculator.divide(1, 0);
        }, "Ділення на нуль повинно викликати ArithmeticException");
    }

    @Test
    public void testSqrt() throws InvalidInputException {
        double result = Calculator.sqrt(4);
        Assertions.assertEquals(2, result, "Квадратний корінь працює неправильно");
    }

    @Test
    public void testSqrtNegative() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            Calculator.sqrt(-3);
        }, "Квадратний корінь з від'ємного числа повинен кидати InvalidInputException");
    }
}
