package com.koev.jsonprocessingcardealer.domain.dto._05_total_sales_by_customer;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.math.BigDecimal;

public class CustomerQuery5Dto implements Dto {

    @Expose
    private String fullName;

    @Expose
    private int boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public CustomerQuery5Dto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
