package com.koev.jsonprocessingcardealer.domain.dto.seed;

import com.google.gson.annotations.Expose;
import com.koev.jsonprocessingcardealer.domain.dto.Dto;

public class SupplierSeedDto implements Dto {

    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public SupplierSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
