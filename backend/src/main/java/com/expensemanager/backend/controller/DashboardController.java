package com.expensemanager.backend.controller;

import com.expensemanager.backend.dto.AnomalyCountDTO;
import com.expensemanager.backend.dto.AnomalyDTO;
import com.expensemanager.backend.dto.MonthlyCategorySummaryDTO;
import com.expensemanager.backend.dto.TopVendorDTO;
import com.expensemanager.backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expensemanager.backend.dto.DashboardDTO;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/monthly-category-totals")
    public List<MonthlyCategorySummaryDTO> getMonthlyCategoryTotals() {

        return dashboardService.getMonthlyCategoryTotals();
    }

    @GetMapping("/top-vendors")
    public List<TopVendorDTO> getTopFiveVendors() {

        return dashboardService.getTopFiveVendors();
    }

    @GetMapping("/anomalies/count")
    public AnomalyCountDTO getAnomalyCount() {

        return dashboardService.getAnomalyCount();
    }

    @GetMapping("/anomalies")
    public List<AnomalyDTO> getAnomalies() {

        return dashboardService.getAnomalies();
    }

    @GetMapping
    public DashboardDTO getDashboard() {

        return dashboardService.getDashboard();
    }
}