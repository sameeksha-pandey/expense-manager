package com.expensemanager.backend.dto;

import java.time.LocalDate;

public class ExpenseResponseDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String vendorName;
    private String description;
    private String category;
    private Boolean isAnomaly;

    public ExpenseResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsAnomaly() {
        return isAnomaly;
    }

    public void setIsAnomaly(Boolean anomaly) {
        isAnomaly = anomaly;
    }
}
