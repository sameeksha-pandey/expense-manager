package com.expensemanager.backend.dto;

public class AnomalyCountDTO {

    private Long count;

    public AnomalyCountDTO(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
}