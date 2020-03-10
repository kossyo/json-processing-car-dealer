package com.koev.jsonprocessingcardealer.repository;

import com.koev.jsonprocessingcardealer.domain.entity.Customer;
import com.koev.jsonprocessingcardealer.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Set<Sale> findAllByCustomer(Customer customer);
}
