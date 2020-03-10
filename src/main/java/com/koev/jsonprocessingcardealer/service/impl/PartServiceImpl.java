package com.koev.jsonprocessingcardealer.service.impl;

import com.koev.jsonprocessingcardealer.domain.dto.Dto;
import com.koev.jsonprocessingcardealer.domain.entity.Part;
import com.koev.jsonprocessingcardealer.domain.entity.Supplier;
import com.koev.jsonprocessingcardealer.repository.PartRepository;
import com.koev.jsonprocessingcardealer.repository.SupplierRepository;
import com.koev.jsonprocessingcardealer.service.PartService;
import com.koev.jsonprocessingcardealer.service.SeedableService;
import com.koev.jsonprocessingcardealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl extends SeedableService implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Random random;
    protected PartServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, PartRepository partRepository, SupplierRepository supplierRepository, Random random) {
        super(modelMapper, random, validatorUtil);
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
//        this.random = random;
        this.random = random;
    }

    @Override
    public void seed(Dto[] partSeedDtos) {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        for (Dto dto : partSeedDtos) {
            Part part = super.getModelMapper().map(dto, Part.class);
            Optional<Supplier> supplier = this.supplierRepository.findById(generateRandomNumber(suppliers.size()));
            part.setSupplier(supplier.get());
            this.partRepository.save(part);
        }
    }

    private int generateRandomNumber(int count) {

        return this.random.nextInt(count) + 1;
    }
}
