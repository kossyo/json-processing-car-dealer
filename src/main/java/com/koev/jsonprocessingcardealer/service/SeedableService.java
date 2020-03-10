package com.koev.jsonprocessingcardealer.service;

import com.koev.jsonprocessingcardealer.domain.dto.Dto;
import com.koev.jsonprocessingcardealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Random;
import java.util.Set;

@Component
public abstract class SeedableService extends BaseService implements Seedable {
    private final ValidatorUtil validatorUtil;

    protected SeedableService(ModelMapper modelMapper, Random random, ValidatorUtil validatorUtil) {
        super(modelMapper, random);
        this.validatorUtil = validatorUtil;
    }

    protected ValidatorUtil getValidatorUtil() {
        return validatorUtil;
    }

    @Override
    public boolean handleViolations(Dto dto) {
        Set<ConstraintViolation<Dto>> violations = this.validatorUtil.getViolations(dto);
        if (violations.size() > 0) {
            violations.forEach(viol -> System.out.println(viol.getMessage()));
            return true;
        }
        return false;
    }
}
