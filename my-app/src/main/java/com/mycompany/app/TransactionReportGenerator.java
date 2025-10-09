package com.mycompany.app;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class TransactionReportGenerator {
    // абстрактні класи зі статичними методами для уникнення створення додаткових екземплярів об'єктів
    private TransactionReportGenerator() {
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }
    
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // Створіть текстовий звіт, що показує сумарні витрати по категоріях та по місяцях.
    // Використовуйте символи (наприклад, `*`), де кожен символ відповідає певній сумі грошей (наприклад, 1000 грн), для візуалізації сум витрат.
    public static void printCategoryExpenseReport(List<Transaction> transactions) {
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                categoryTotals.put(t.getDescription(), categoryTotals.getOrDefault(t.getDescription(), 0.0) + Math.abs(t.getAmount()));
            }
        }

        System.out.println("Витрати по категоріях:");
        categoryTotals.forEach((category, total) -> {
            int hashes = (int)(total / 10000);
            int stars = (int)((total % 10000) / 1000);
            int dollars = (int)((total % 1000) / 100);

            String symbols = "#".repeat(hashes) + "*".repeat(stars) + "$".repeat(dollars);
            System.out.println(category + ": " + total + " " + symbols);
        });

        System.out.println();
    }

    // Створіть текстовий звіт, що показує сумарні витрати по категоріях та по місяцях.
    // Використовуйте символи (наприклад, `*`), де кожен символ відповідає певній сумі грошей (наприклад, 1000 грн), для візуалізації сум витрат.
    public static void printMonthlyExpenseReport(List<Transaction> transactions) {
        Map<String, Double> monthTotals = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                String month = t.getDate().substring(3);
                monthTotals.put(month, monthTotals.getOrDefault(month, 0.0) + Math.abs(t.getAmount()));
            }
        }

        System.out.println("Витрати по місяцях:");
        monthTotals.forEach((month, total) -> {
        int hashes = (int)(total / 10000);
        int stars = (int)((total % 10000) / 1000);
        int dollars = (int)((total % 1000) / 100);

        String symbols = "#".repeat(hashes) + "*".repeat(stars) + "$".repeat(dollars);
        System.out.println(month + ": " + total + " " + symbols);
    });
        System.out.println();
    }
}
