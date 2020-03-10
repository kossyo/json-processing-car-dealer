package com.koev.jsonprocessingcardealer.domain.dto._02_cars_from_make_toyota;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public class CarDto implements Dto {

    @Expose
    private int id;

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

    public CarDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
