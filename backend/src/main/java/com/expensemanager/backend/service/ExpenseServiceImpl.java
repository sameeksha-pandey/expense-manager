package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.ExpenseRequestDTO;
import com.expensemanager.backend.dto.ExpenseResponseDTO;
import com.expensemanager.backend.entity.Expense;
import com.expensemanager.backend.mapper.ExpenseMapper;
import com.expensemanager.backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final VendorCategoryService vendorCategoryService;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            VendorCategoryService vendorCategoryService,
            ExpenseMapper expenseMapper) {

        this.expenseRepository = expenseRepository;
        this.vendorCategoryService = vendorCategoryService;
        this.expenseMapper = expenseMapper;
    }

    @Override
public ExpenseResponseDTO addExpense(ExpenseRequestDTO request) {

    Expense savedExpense = processExpense(request);

    return expenseMapper.toResponseDTO(savedExpense);
  }
  @Override
   public void processExpenseFromCsv(ExpenseRequestDTO request) {
    processExpense(request);
}


  public Expense processExpense(ExpenseRequestDTO request) {

    Expense expense = expenseMapper.toEntity(request);

    String category =
            vendorCategoryService.categorize(request.getVendorName());

    expense.setCategory(category);

    Double averageAmount =
            expenseRepository.findAverageAmountByCategory(category);

    boolean isAnomaly = false;

    if (averageAmount != null &&
            request.getAmount() > 3 * averageAmount) {
        isAnomaly = true;
    }

    expense.setIsAnomaly(isAnomaly);

    return expenseRepository.save(expense);
  }

}