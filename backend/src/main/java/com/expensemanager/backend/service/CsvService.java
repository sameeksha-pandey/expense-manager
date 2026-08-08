package com.expensemanager.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvService {

    int importExpenses(MultipartFile file);

}