package com.mycompany.app;
import java.util.Scanner;

//  - У головному методі main реалізуйте взаємодію з користувачем.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть операцію +, -, *, /, sqrt: ");
            String operation = scanner.nextLine();

            double result;

            switch (operation) {
                case "+":
                    result = Calculator.add(a, b);
                    System.out.println("Результат додавання: " + result);
                    break;
                case "-":
                    result = Calculator.subtract(a, b);
                    System.out.println("Результат віднімання: " + result);
                    break;
                case "*":
                    result = Calculator.multiply(a, b);
                    System.out.println("Результат множення: " + result);
                    break;
                case "/":
                    result = Calculator.divide(a, b);
                    System.out.println("Результат ділення: " + result);
                    break;
                case "sqrt":
                    result = Calculator.sqrt(a);
                    System.out.println("Результат квадратний корінь: " + result);
                    break;
                default:
                    System.out.println("Введена невідома операція");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено не число");
        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            System.out.println("Обробка запиту завершена");
        }

        scanner.close();
    }
}
