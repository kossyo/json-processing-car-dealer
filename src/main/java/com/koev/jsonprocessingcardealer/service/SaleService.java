package com.koev.jsonprocessingcardealer.service;

import com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount.SaleQuery6Dto;

import java.util.Set;

public interface SaleService  {
    void createSalesRelations();

    Set<SaleQuery6Dto> getAll();
}
