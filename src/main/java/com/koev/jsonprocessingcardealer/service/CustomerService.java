package com.koev.jsonprocessingcardealer.service;

import com.koev.jsonprocessingcardealer.domain.dto._01_ordered_customers.CustomerDto;
import com.koev.jsonprocessingcardealer.domain.dto._05_total_sales_by_customer.CustomerQuery5Dto;

import java.util.List;
import java.util.Set;

public interface CustomerService extends Seedable {
    Set<CustomerDto> getOrderedCustomers();

    List<CustomerQuery5Dto> allCustomersWhoBoughtACar();
}
