package com.koev.jsonprocessingcardealer.domain.dto._01_ordered_customers;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.util.Set;

public class CustomerDto implements Dto {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String birthDate;

    @Expose
    private boolean isYoungDriver;

    @Expose
    private Set<SaleDto> sales;

    public CustomerDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDto> sales) {
        this.sales = sales;
    }
}
