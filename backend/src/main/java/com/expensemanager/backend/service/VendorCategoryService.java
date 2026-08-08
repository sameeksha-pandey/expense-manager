package com.expensemanager.backend.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VendorCategoryService {

    private final Map<String, String> vendorCategoryMap = new HashMap<>();

    public VendorCategoryService() {

        vendorCategoryMap.put("swiggy", "Food");
        vendorCategoryMap.put("zomato", "Food");

        vendorCategoryMap.put("uber", "Travel");
        vendorCategoryMap.put("ola", "Travel");

        vendorCategoryMap.put("amazon", "Shopping");
        vendorCategoryMap.put("flipkart", "Shopping");

        vendorCategoryMap.put("netflix", "Entertainment");
        vendorCategoryMap.put("spotify", "Entertainment");
    }

    public String categorize(String vendorName) {

        if (vendorName == null || vendorName.trim().isEmpty()) {
            return "Others";
        }

        String normalizedVendor = vendorName.trim().toLowerCase();

        return vendorCategoryMap.getOrDefault(normalizedVendor, "Others");
    }
}