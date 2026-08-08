package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.MonthlyCategorySummaryDTO;
import com.expensemanager.backend.dto.TopVendorDTO;
import com.expensemanager.backend.dto.AnomalyCountDTO;
import com.expensemanager.backend.dto.AnomalyDTO;
import com.expensemanager.backend.dto.DashboardDTO;
import java.util.List;

public interface DashboardService {

    List<MonthlyCategorySummaryDTO> getMonthlyCategoryTotals();

    List<TopVendorDTO> getTopFiveVendors();

    AnomalyCountDTO getAnomalyCount();

    List<AnomalyDTO> getAnomalies();

    DashboardDTO getDashboard();
}