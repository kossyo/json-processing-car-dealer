package com.koev.jsonprocessingcardealer.service.impl;

import com.koev.jsonprocessingcardealer.domain.dto.Dto;
import com.koev.jsonprocessingcardealer.domain.dto._02_cars_from_make_toyota.CarDto;

import com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts.CarAndPartsWrapperDto;
import com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts.CarQuery4Dto;
import com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts.PartQuery4Dto;
import com.koev.jsonprocessingcardealer.domain.entity.Car;
import com.koev.jsonprocessingcardealer.domain.entity.Part;
import com.koev.jsonprocessingcardealer.repository.CarRepository;
import com.koev.jsonprocessingcardealer.repository.PartRepository;
import com.koev.jsonprocessingcardealer.service.CarService;
import com.koev.jsonprocessingcardealer.service.SeedableService;
import com.koev.jsonprocessingcardealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl extends SeedableService implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Random random;

    protected CarServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, CarRepository carRepository, PartRepository partRepository, Random random) {
        super(modelMapper, random, validatorUtil);
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.random = random;
    }

    @Override
    public void seed(Dto[] carSeedDtos) {
        List<Part> parts = this.partRepository.findAll();
        for (Dto dto : carSeedDtos) {

            Car car = super.getModelMapper().map(dto, Car.class);
            assignPartsToCar(car, parts);
            this.carRepository.save(car);
        }
    }

    @Override
    public Set<CarDto> carsFromMakeToyota() {
        Set<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        Set<CarDto> carDtos = new LinkedHashSet<>();

        for (Car car : cars) {
            CarDto carDto = super.getModelMapper().map(car, CarDto.class);
            carDtos.add(carDto);
        }
        return carDtos;
    }

    @Override
    public Set<CarAndPartsWrapperDto> findAllCarsQuery4(){

        Set<CarAndPartsWrapperDto> carAndPartsWrapperDtos = new LinkedHashSet<>();
        List<Car> cars = this.carRepository.findAll();
        for (Car car : cars) {
            CarQuery4Dto carQuery4Dto = super.getModelMapper().map(car, CarQuery4Dto.class);
            Set<PartQuery4Dto> partQuery4Dtos = new LinkedHashSet<>();
            for (Part part : car.getParts()) {
                PartQuery4Dto partQuery4Dto = super.getModelMapper().map(part, PartQuery4Dto.class);
                partQuery4Dtos.add(partQuery4Dto);
            }
            CarAndPartsWrapperDto carAndPartsWrapperDto = new CarAndPartsWrapperDto();
            carAndPartsWrapperDto.setCar(carQuery4Dto);
            carAndPartsWrapperDto.setParts(partQuery4Dtos);
            carAndPartsWrapperDtos.add(carAndPartsWrapperDto);
        }
        return carAndPartsWrapperDtos;
    }

    private void assignPartsToCar(Car car, List<Part> parts) {

        int howManyPartsToAssign = generateRandomNumber(10) + 10;
        int partsCount = parts.size();
        for (int i = 0; i < howManyPartsToAssign; i++) {
            int idOfRandomPart = generateRandomNumber(partsCount - 1);
            Part part = parts.get(idOfRandomPart);
            car.addPart(part);
        }
    }

    //    todo: try to pull this random up
    private int generateRandomNumber(int count) {

        return this.random.nextInt(count) + 1;
    }


}
