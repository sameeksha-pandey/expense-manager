package com.expensemanager.backend.mapper;

import com.expensemanager.backend.dto.ExpenseRequestDTO;
import com.expensemanager.backend.dto.ExpenseResponseDTO;
import com.expensemanager.backend.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public Expense toEntity(ExpenseRequestDTO request) {

        Expense expense = new Expense();

        expense.setDate(request.getDate());
        expense.setAmount(request.getAmount());
        expense.setVendorName(request.getVendorName());
        expense.setDescription(request.getDescription());

        return expense;
    }

    public ExpenseResponseDTO toResponseDTO(Expense expense) {

        ExpenseResponseDTO response = new ExpenseResponseDTO();

        response.setId(expense.getId());
        response.setDate(expense.getDate());
        response.setAmount(expense.getAmount());
        response.setVendorName(expense.getVendorName());
        response.setDescription(expense.getDescription());
        response.setCategory(expense.getCategory());
        response.setIsAnomaly(expense.getIsAnomaly());

        return response;
    }
}
