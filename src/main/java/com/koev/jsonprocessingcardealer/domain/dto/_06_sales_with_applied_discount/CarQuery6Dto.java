package com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public class CarQuery6Dto implements Dto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

    public CarQuery6Dto() {
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
