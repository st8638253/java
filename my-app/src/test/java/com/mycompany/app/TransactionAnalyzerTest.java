package com.mycompany.app;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    // Тест на визначення 10 найбільших витрат.
    @Test
    void testFindTopExpenses() {
        List<Transaction> transactions = List.of(
                new Transaction("01-10-2025", -500.0, "Сільпо"),
                new Transaction("02-10-2025", -50.0, "Аптека"),
                new Transaction("03-10-2025", -200.0, "Комунальні послуги"),
                new Transaction("04-10-2025", -1000.0, "Подарунки"),
                new Transaction("05-10-2025", -20.0, "Кав'ярня"),
                new Transaction("06-10-2025", -300.0, "Бензин"),
                new Transaction("07-10-2025", -150.0, "Ресторан"),
                new Transaction("08-10-2025", -700.0, "Кінотеатр"),
                new Transaction("09-10-2025", -400.0, "Новорічні прикраси"),
                new Transaction("10-10-2025", -80.0, "Сільпо"),
                new Transaction("11-10-2025", -60.0, "Аптека"),
                new Transaction("12-10-2025", -900.0, "Бензин")
        );

        // TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Список топ-10 витрат повинен містити 10 елементів");

        for (int i = 0; i < topExpenses.size() - 1; i++) {
            Assertions.assertTrue(topExpenses.get(i).getAmount() <= topExpenses.get(i + 1).getAmount(),
                    "Список топ-10 витрат повинен бути відсортований за сумою");
        }
    }
}
