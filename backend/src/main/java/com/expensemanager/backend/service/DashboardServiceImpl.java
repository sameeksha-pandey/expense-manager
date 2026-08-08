package com.expensemanager.backend.service;

import com.expensemanager.backend.dto.AnomalyCountDTO;
import com.expensemanager.backend.dto.AnomalyDTO;
import com.expensemanager.backend.dto.MonthlyCategorySummaryDTO;
import com.expensemanager.backend.dto.TopVendorDTO;
import com.expensemanager.backend.entity.Expense;
import com.expensemanager.backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import com.expensemanager.backend.dto.DashboardDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

        private final ExpenseRepository expenseRepository;

        public DashboardServiceImpl(ExpenseRepository expenseRepository) {
                this.expenseRepository = expenseRepository;
        }

        @Override
        public List<MonthlyCategorySummaryDTO> getMonthlyCategoryTotals() {

                List<Object[]> results = expenseRepository.getMonthlyCategoryTotals();

                List<MonthlyCategorySummaryDTO> response = new ArrayList<>();

                for (Object[] row : results) {

                        String month = (String) row[0];

                        String category = (String) row[1];

                        Double totalAmount = ((Number) row[2]).doubleValue();

                        response.add(
                                        new MonthlyCategorySummaryDTO(
                                                        month,
                                                        category,
                                                        totalAmount));
                }

                return response;
        }

        @Override
        public List<TopVendorDTO> getTopFiveVendors() {

                List<Object[]> results = expenseRepository.getTopFiveVendors();

                List<TopVendorDTO> response = new ArrayList<>();

                for (Object[] row : results) {

                        String vendorName = (String) row[0];

                        Double totalAmount = ((Number) row[1]).doubleValue();

                        response.add(
                                        new TopVendorDTO(
                                                        vendorName,
                                                        totalAmount));
                }

                return response;
        }

        @Override
        public AnomalyCountDTO getAnomalyCount() {

                Long count = expenseRepository.countAnomalies();

                return new AnomalyCountDTO(count);
        }

        @Override
        public List<AnomalyDTO> getAnomalies() {

                List<Expense> anomalies = expenseRepository.findAnomalies();

                List<AnomalyDTO> response = new ArrayList<>();

                for (Expense expense : anomalies) {

                        response.add(
                                        new AnomalyDTO(
                                                        expense.getId(),
                                                        expense.getDate(),
                                                        expense.getAmount(),
                                                        expense.getVendorName(),
                                                        expense.getDescription(),
                                                        expense.getCategory()));
                }

                return response;
        }

        @Override
        public DashboardDTO getDashboard() {

                List<MonthlyCategorySummaryDTO> monthlyCategoryTotals = getMonthlyCategoryTotals();

                List<TopVendorDTO> topVendors = getTopFiveVendors();

                AnomalyCountDTO anomalyCount = getAnomalyCount();

                List<AnomalyDTO> anomalies = getAnomalies();

                return new DashboardDTO(
                                monthlyCategoryTotals,
                                topVendors,
                                anomalyCount,
                                anomalies);
        }
}