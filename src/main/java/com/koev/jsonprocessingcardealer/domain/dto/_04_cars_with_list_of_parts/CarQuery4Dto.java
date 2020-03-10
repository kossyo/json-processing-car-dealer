package com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.util.Set;

public class CarQuery4Dto implements Dto {

//    private int id;

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

//    @Expose
//    private Set<PartQuery4Dto> parts;



    public CarQuery4Dto() {
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

//    public Set<PartQuery4Dto> getParts() {
//        return parts;
//    }
//
//    public void setParts(Set<PartQuery4Dto> parts) {
//        this.parts = parts;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
