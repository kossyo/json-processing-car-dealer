package com.koev.jsonprocessingcardealer.controller;

import com.google.gson.Gson;
import com.koev.jsonprocessingcardealer.constants.Constants;
import com.koev.jsonprocessingcardealer.constants.JsonConstants;
import com.koev.jsonprocessingcardealer.domain.dto._01_ordered_customers.CustomerDto;
import com.koev.jsonprocessingcardealer.domain.dto._02_cars_from_make_toyota.CarDto;
import com.koev.jsonprocessingcardealer.domain.dto._03_local_suppliers.SupplierDto;
import com.koev.jsonprocessingcardealer.domain.dto._04_cars_with_list_of_parts.CarAndPartsWrapperDto;
import com.koev.jsonprocessingcardealer.domain.dto._05_total_sales_by_customer.CustomerQuery5Dto;
import com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount.SaleQuery6Dto;
import com.koev.jsonprocessingcardealer.domain.dto.seed.CarSeedDto;
import com.koev.jsonprocessingcardealer.domain.dto.seed.CustomerSeedDto;
import com.koev.jsonprocessingcardealer.domain.dto.seed.PartSeedDto;
import com.koev.jsonprocessingcardealer.domain.dto.seed.SupplierSeedDto;
import com.koev.jsonprocessingcardealer.service.*;
import com.koev.jsonprocessingcardealer.util.FileUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class AppController implements CommandLineRunner {

    private static final String CARS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\cars.json";
    private static final String CUSTOMERS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\customers.json";
    private static final String PARTS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\parts.json";
    private static final String SUPPLIERS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\suppliers.json";

    private final Gson gson;
    private final FileUtil fileUtil;

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public AppController(Gson gson, FileUtil fileUtil, SupplierService supplierService, PartService partService,
                         CarService carService, CustomerService customerService, SaleService saleService) {
        this.gson = gson;

        this.fileUtil = fileUtil;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        createDbRecords();
        orderedCustomers();
        carsFromMakeToyota();
        localSuppliers();
        carWithListOfParts();
        totalSalesByCustomer();
        salesWithAppliedDiscount();
    }

    //    Query 1 – Ordered Customers
    private void orderedCustomers() throws IOException {

        Set<CustomerDto> orderedCustomers = this.customerService.getOrderedCustomers();
        String orderedCustomersString = this.gson.toJson(orderedCustomers);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_ORDERED_CUSTOMERS, orderedCustomersString);
    }

    //    Query 2 – Cars from make Toyota
    private void carsFromMakeToyota() throws IOException {
        Set<CarDto> toyotas = this.carService.carsFromMakeToyota();
        String toyotasString = this.gson.toJson(toyotas);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_TOYOTA_CARS, toyotasString);
    }

    //    Query 3 – Local Suppliers
    private void localSuppliers() throws IOException {
        Set<SupplierDto> supplierDtos = this.supplierService.localSuppliers();
        String suppliersString = this.gson.toJson(supplierDtos);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_LOCAL_SUPPLIERS, suppliersString);
    }

    //    Query 4 – Cars with Their List of Parts
    private void carWithListOfParts() throws IOException {
        Set<CarAndPartsWrapperDto> allCarsQuery4 = this.carService.findAllCarsQuery4();
        String carsQuery4String = this.gson.toJson(allCarsQuery4);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_CARS_AND_PARTS, carsQuery4String);
    }

    //    Query 5 – Total Sales by Customer
    private void totalSalesByCustomer() throws IOException {
        List<CustomerQuery5Dto> customerDtos = this.customerService.allCustomersWhoBoughtACar();
        String customersString = this.gson.toJson(customerDtos);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_TOTAL_SALES_BY_CUSTOMER, customersString);
    }

    //    Query 6 – Sales with Applied Discount
    private void salesWithAppliedDiscount() throws IOException {
        Set<SaleQuery6Dto> sales = this.saleService.getAll();
        String salesString = this.gson.toJson(sales);
        this.fileUtil.writeFile(Constants.OUTPUT_FILE_PATH_SALES_WITH_APPLIED_DISCOUNT, salesString);

    }













    private void createDbRecords() throws IOException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        linkSales();
    }

    private void seedSuppliers() throws IOException {
        String suppliersContent = this.fileUtil.getFileContent(SUPPLIERS_JSON_FILE_PATH);
        SupplierSeedDto[] userSeedDtos = this.gson.fromJson(suppliersContent, SupplierSeedDto[].class);
        this.supplierService.seed(userSeedDtos);
    }

    private void seedParts() throws IOException {
        String partContent = this.fileUtil.getFileContent(PARTS_JSON_FILE_PATH);
        PartSeedDto[] carSeedDtos = this.gson.fromJson(partContent, PartSeedDto[].class);
        this.partService.seed(carSeedDtos);
    }

    private void seedCars() throws IOException {
        String carsContent = this.fileUtil.getFileContent(CARS_JSON_FILE_PATH);
        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsContent, CarSeedDto[].class);
        this.carService.seed(carSeedDtos);
    }

    private void seedCustomers() throws IOException {
        String customersContent = this.fileUtil.getFileContent(CUSTOMERS_JSON_FILE_PATH);
        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(customersContent, CustomerSeedDto[].class);
        this.customerService.seed(customerSeedDtos);
    }

    private void linkSales() {
        this.saleService.createSalesRelations();
    }



}
