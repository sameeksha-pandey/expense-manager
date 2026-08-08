package com.expensemanager.backend.controller;

import com.expensemanager.backend.dto.ExpenseRequestDTO;
import com.expensemanager.backend.dto.ExpenseResponseDTO;
import com.expensemanager.backend.service.CsvService;
import com.expensemanager.backend.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final CsvService csvService;

    public ExpenseController(
            ExpenseService expenseService,
            CsvService csvService) {

        this.expenseService = expenseService;
        this.csvService = csvService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponseDTO addExpense(@RequestBody ExpenseRequestDTO request) {
        return expenseService.addExpense(request);
    }

    @PostMapping("/upload-csv")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {

        int importedCount = csvService.importExpenses(file);

        return importedCount + " expenses imported successfully.";
    }
}