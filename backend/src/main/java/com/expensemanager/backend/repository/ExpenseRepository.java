package com.expensemanager.backend.repository;

import com.expensemanager.backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT AVG(e.amount) FROM Expense e WHERE e.category = :category")
    Double findAverageAmountByCategory(@Param("category") String category);

    @Query(value = """
        SELECT
            TO_CHAR(date, 'YYYY-MM') AS month,
            category,
            SUM(amount) AS total_amount
        FROM expenses
        GROUP BY TO_CHAR(date, 'YYYY-MM'), category
        ORDER BY TO_CHAR(date, 'YYYY-MM'), category
        """, nativeQuery = true) 
    List<Object[]> getMonthlyCategoryTotals();

    @Query(value = """
        SELECT
            vendor_name,
            SUM(amount) AS total_amount
        FROM expenses
        GROUP BY vendor_name
        ORDER BY total_amount DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Object[]> getTopFiveVendors();

    @Query("""
        SELECT COUNT(e)
        FROM Expense e
        WHERE e.isAnomaly = true
        """)
    Long countAnomalies();

    @Query("""
        SELECT e
        FROM Expense e
        WHERE e.isAnomaly = true
        ORDER BY e.date DESC
        """)
    List<Expense> findAnomalies();
}
