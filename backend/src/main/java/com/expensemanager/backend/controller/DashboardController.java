package com.expensemanager.backend.controller;

import com.expensemanager.backend.dto.MonthlyCategorySummaryDTO;
import com.expensemanager.backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expensemanager.backend.dto.TopVendorDTO;
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
}

