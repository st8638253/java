package com.mycompany.app;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        // TransactionCSVReader csvReader = new TransactionCSVReader();

        List<String> rawData = TransactionCSVReader.readRawData(filePath);

        List<Transaction> transactions = TransactionCSVReader.parseTransactions(rawData);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        // TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        // TransactionReportGenerator reportGenerator = new TransactionReportGenerator();
        
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        // System.out.println("Загальний баланс: " + totalBalance);
        TransactionReportGenerator.printBalanceReport(totalBalance);
        
        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        // System.out.println("Кількість транзакцій за " + monthYear + ": " + transactionsCount);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // Додайте функцію для визначення найбільших і найменших витрат за вказаний період.
        List<Transaction> expensiveCheapest = TransactionAnalyzer.findExpensiveAndCheapest(transactions, monthYear);
        if (!expensiveCheapest.isEmpty()) {
            System.out.println("Найбільша витрата: " + expensiveCheapest.get(0).getDescription() + ": " + expensiveCheapest.get(0).getAmount());
            System.out.println("Найменша витрата: " + expensiveCheapest.get(1).getDescription() + ": " + expensiveCheapest.get(1).getAmount());
        }

        // Створіть текстовий звіт, що показує сумарні витрати по категоріях та по місяцях.
        // Використовуйте символи (наприклад, `*`), де кожен символ відповідає певній сумі грошей (наприклад, 1000 грн), для візуалізації сум витрат.
        TransactionReportGenerator.printCategoryExpenseReport(transactions);
        TransactionReportGenerator.printMonthlyExpenseReport(transactions);
    }
}
