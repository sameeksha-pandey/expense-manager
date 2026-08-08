package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.ExpenseRequestDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class CsvServiceImpl implements CsvService {

    private final ExpenseServiceImpl expenseService;

    public CsvServiceImpl(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public int importExpenses(MultipartFile file) {

        int importedCount = 0;

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                file.getInputStream(),
                                StandardCharsets.UTF_8
                        )
                );

                CSVParser csvParser = CSVFormat.DEFAULT.builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setIgnoreEmptyLines(true)
                        .build()
                        .parse(reader)
        ) {

            for (CSVRecord record : csvParser) {

                ExpenseRequestDTO request = new ExpenseRequestDTO();

                request.setDate(
                        LocalDate.parse(record.get("date").trim())
                );

                request.setAmount(
                        Double.parseDouble(record.get("amount").trim())
                );

                request.setVendorName(
                        record.get("vendorName").trim()
                );

                request.setDescription(
                        record.get("description").trim()
                );

                expenseService.processExpenseFromCsv(request);

                importedCount++;
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to process CSV file: " + e.getMessage(),
                    e
            );
        }

        return importedCount;
    }
}