package com.mycompany.app;

// Реалізуйте клас Calculator
public class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Не можна ділити на нуль!");
        }
        return a / b;
    }

    // не менше нуля для квадратного кореня
    public static double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("З від’ємного числа не знайти значення");
        }
        return Math.sqrt(a);
    }
}
