package com.mycompany.app;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private TransactionAnalyzer(List<Transaction> transactions) {
    }

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
    
    // Тут будуть інші методи для аналізу транзакцій
    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
            .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
            .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
            .limit(10) // Обмеження результату першими 10 записами
            .collect(Collectors.toList()); // Збір результату в список
        }

    // Додайте функцію для визначення найбільших і найменших витрат за вказаний період.
    public static List<Transaction> findExpensiveAndCheapest(List<Transaction> transactions, String monthYear) {
        Transaction cheapest = null;
        Transaction expensive = null;

        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate(), DATE_FORMATTER);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (!transactionMonthYear.equals(monthYear)) {
                continue;
            }
            if (t.getAmount() > 0) {
                continue;
            }

            if (cheapest == null || t.getAmount() < cheapest.getAmount()) {
                cheapest = t;
            }
            if (expensive == null || t.getAmount() > expensive.getAmount()) {
                expensive = t;
            }
        }

        if (cheapest == null || expensive == null) {
            return List.of();
        }

        return List.of(cheapest, expensive);
    }
}
