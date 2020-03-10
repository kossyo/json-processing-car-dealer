package com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.util.Set;

public class CarAndPartsWrapperDto implements Dto {

    @Expose
    private CarQuery4Dto car;

    @Expose
    private Set<PartQuery4Dto> parts;

    public CarAndPartsWrapperDto() {
    }

    public CarQuery4Dto getCar() {
        return car;
    }

    public void setCar(CarQuery4Dto car) {
        this.car = car;
    }

    public Set<PartQuery4Dto> getParts() {
        return parts;
    }

    public void setParts(Set<PartQuery4Dto> parts) {
        this.parts = parts;
    }
}
