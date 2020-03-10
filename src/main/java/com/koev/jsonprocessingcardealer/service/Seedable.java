package com.koev.jsonprocessingcardealer.service;


import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public interface Seedable {

    void seed(Dto[] Dtos);

    boolean handleViolations(Dto dto);
}
