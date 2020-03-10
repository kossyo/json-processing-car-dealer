package com.koev.jsonprocessingcardealer.service;

import com.koev.jsonprocessingcardealer.domain.dto._02_cars_from_make_toyota.CarDto;
import com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts.CarAndPartsWrapperDto;

import java.util.Set;

public interface CarService extends Seedable {
    Set<CarDto> carsFromMakeToyota();

    Set<CarAndPartsWrapperDto> findAllCarsQuery4();
}
