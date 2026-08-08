package com.expensemanager.backend.dto;

public class MonthlyCategorySummaryDTO {

    private String month;
    private String category;
    private Double totalAmount;

    public MonthlyCategorySummaryDTO(
            String month,
            String category,
            Double totalAmount) {

        this.month = month;
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        return month;
    }

    public String getCategory() {
        return category;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
