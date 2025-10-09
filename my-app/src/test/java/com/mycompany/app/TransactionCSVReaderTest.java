package com.mycompany.app;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class TransactionCSVReaderTest {
    // Напишіть тест для перевірки читання даних із CSV.
    @Test
    void testReadRawData() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        // TransactionCSVReader reader = new TransactionCSVReader();
        List<String> lines = TransactionCSVReader.readRawData(filePath);

        Assertions.assertNotNull(lines, "CSV дані не повинні бути null");
        Assertions.assertTrue(lines.size() > 0, "CSV повинен містити хоча б один рядок");
    }
}
