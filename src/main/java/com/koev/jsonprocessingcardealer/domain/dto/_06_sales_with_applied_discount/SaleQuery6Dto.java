package com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.math.BigDecimal;

public class SaleQuery6Dto implements Dto {

    @Expose
    private CarQuery6Dto car;

    @Expose
    private String customerName;

    @Expose
    private double discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithoutDiscount;

    public SaleQuery6Dto() {
    }

    public CarQuery6Dto getCar() {
        return car;
    }

    public void setCar(CarQuery6Dto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(BigDecimal priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }
}
