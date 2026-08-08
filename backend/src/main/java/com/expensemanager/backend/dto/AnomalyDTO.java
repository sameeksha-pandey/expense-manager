package com.expensemanager.backend.dto;

import java.time.LocalDate;

public class AnomalyDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String vendorName;
    private String description;
    private String category;

    public AnomalyDTO(
            Long id,
            LocalDate date,
            Double amount,
            String vendorName,
            String description,
            String category) {

        this.id = id;
        this.date = date;
        this.amount = amount;
        this.vendorName = vendorName;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}