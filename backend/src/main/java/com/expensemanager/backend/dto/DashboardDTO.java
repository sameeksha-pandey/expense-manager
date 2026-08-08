package com.expensemanager.backend.dto;

import java.util.List;

public class DashboardDTO {

    private List<MonthlyCategorySummaryDTO> monthlyCategoryTotals;
    private List<TopVendorDTO> topVendors;
    private AnomalyCountDTO anomalyCount;
    private List<AnomalyDTO> anomalies;

    public DashboardDTO(
            List<MonthlyCategorySummaryDTO> monthlyCategoryTotals,
            List<TopVendorDTO> topVendors,
            AnomalyCountDTO anomalyCount,
            List<AnomalyDTO> anomalies) {

        this.monthlyCategoryTotals = monthlyCategoryTotals;
        this.topVendors = topVendors;
        this.anomalyCount = anomalyCount;
        this.anomalies = anomalies;
    }

    public List<MonthlyCategorySummaryDTO> getMonthlyCategoryTotals() {
        return monthlyCategoryTotals;
    }

    public List<TopVendorDTO> getTopVendors() {
        return topVendors;
    }

    public AnomalyCountDTO getAnomalyCount() {
        return anomalyCount;
    }

    public List<AnomalyDTO> getAnomalies() {
        return anomalies;
    }
}