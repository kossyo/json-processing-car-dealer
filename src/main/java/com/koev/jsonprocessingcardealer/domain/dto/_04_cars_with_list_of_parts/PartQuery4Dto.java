package com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

import java.math.BigDecimal;

public class PartQuery4Dto implements Dto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public PartQuery4Dto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
