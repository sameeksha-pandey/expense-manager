package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.MonthlyCategorySummaryDTO;
import com.expensemanager.backend.dto.TopVendorDTO;

import java.util.List;

public interface DashboardService {

    List<MonthlyCategorySummaryDTO> getMonthlyCategoryTotals();

    List<TopVendorDTO> getTopFiveVendors();
}