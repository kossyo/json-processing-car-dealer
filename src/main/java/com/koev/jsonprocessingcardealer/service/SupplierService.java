package com.koev.jsonprocessingcardealer.service;

import com.koev.jsonprocessingcardealer.domain.dto._03_local_suppliers.SupplierDto;

import java.util.Set;

public interface SupplierService extends Seedable {
    Set<SupplierDto> localSuppliers();
}
