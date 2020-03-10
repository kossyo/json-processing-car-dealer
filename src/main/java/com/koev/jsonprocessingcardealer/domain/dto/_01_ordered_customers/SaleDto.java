package com.koev.jsonprocessingcardealer.domain.dto._01_ordered_customers;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public class SaleDto implements Dto {

    @Expose
    private double discount;

    @Expose
    private String carMake;

    @Expose
    private String carModel;

    @Expose
    private String customerName;

    @Expose
    private String customerBirthDate;

    public SaleDto(){}

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(String customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }
}
