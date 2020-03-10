package com.koev.jsonprocessingcardealer.service.impl;

import com.koev.jsonprocessingcardealer.domain.dto.Dto;
import com.koev.jsonprocessingcardealer.domain.dto._03_local_suppliers.SupplierDto;
import com.koev.jsonprocessingcardealer.domain.entity.Supplier;
import com.koev.jsonprocessingcardealer.repository.SupplierRepository;
import com.koev.jsonprocessingcardealer.service.SeedableService;
import com.koev.jsonprocessingcardealer.service.SupplierService;
import com.koev.jsonprocessingcardealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Service
public class SupplierServiceImpl extends SeedableService implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final Random random;

    protected SupplierServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, SupplierRepository supplierRepository, Random random) {
        super(modelMapper, random, validatorUtil);
        this.supplierRepository = supplierRepository;
        this.random = random;
    }

    @Override
    public void seed(Dto[] supplierSeedDtos) {
        for (Dto dto : supplierSeedDtos) {

            Supplier supplier = super.getModelMapper().map(dto, Supplier.class);
            this.supplierRepository.save(supplier);
        }
    }

    @Override
    public Set<SupplierDto> localSuppliers() {
        Set<Supplier> suppliers = this.supplierRepository.localSuppliers();
        Set<SupplierDto> supplierDtos = new LinkedHashSet<>();

        for (Supplier supplier : suppliers) {
            SupplierDto dto = super.getModelMapper().map(supplier, SupplierDto.class);
            dto.setPartsCount(supplier.getParts().size());
            supplierDtos.add(dto);
        }

        return supplierDtos;
    }
}
