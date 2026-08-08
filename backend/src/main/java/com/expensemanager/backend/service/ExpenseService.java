package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.ExpenseRequestDTO;
import com.expensemanager.backend.dto.ExpenseResponseDTO;

public interface ExpenseService {

    ExpenseResponseDTO addExpense(ExpenseRequestDTO request);

    void processExpenseFromCsv(ExpenseRequestDTO request);

}