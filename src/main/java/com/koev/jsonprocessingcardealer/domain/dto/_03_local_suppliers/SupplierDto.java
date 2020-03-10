package com.koev.jsonprocessingcardealer.domain.dto._03_local_suppliers;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public class SupplierDto implements Dto {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private int partsCount;

    public SupplierDto() {
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

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
