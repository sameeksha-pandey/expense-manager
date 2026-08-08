package com.expensemanager.backend.dto;

public class TopVendorDTO {

    private String vendorName;
    private Double totalAmount;

    public TopVendorDTO(String vendorName, Double totalAmount) {
        this.vendorName = vendorName;
        this.totalAmount = totalAmount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
