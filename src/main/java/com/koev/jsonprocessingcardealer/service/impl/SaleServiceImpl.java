package com.koev.jsonprocessingcardealer.service.impl;

import com.koev.jsonprocessingcardealer.constants.Constants;
import com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount.CarQuery6Dto;
import com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount.SaleQuery6Dto;
//import com.koev.jsonprocessingcardealer.domain.dto._06_sales_with_applied_discount.TheRestOfQuery6Dto;
import com.koev.jsonprocessingcardealer.domain.entity.Car;
import com.koev.jsonprocessingcardealer.domain.entity.Customer;
import com.koev.jsonprocessingcardealer.domain.entity.Sale;
import com.koev.jsonprocessingcardealer.repository.CarRepository;
import com.koev.jsonprocessingcardealer.repository.CustomerRepository;
import com.koev.jsonprocessingcardealer.repository.SaleRepository;
import com.koev.jsonprocessingcardealer.service.BaseService;
import com.koev.jsonprocessingcardealer.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl extends BaseService implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EntityManager em;

    public SaleServiceImpl(ModelMapper modelMapper, Random random, SaleRepository saleRepository,
                           CarRepository carRepository, CustomerRepository customerRepository, EntityManager em) {
        super(modelMapper, random);
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.em = em;
    }

    @Override
    @Transactional
    public void createSalesRelations() {
        List<Customer> customers = this.customerRepository.findAll();
        int customersCount = customers.size();
        List<Car> cars = this.carRepository.findAll();
        int carsCount = cars.size();

        for (int i = 0; i < Constants.NUMBER_OF_SALES; i++) {

            int customerId = super.getRandom().nextInt(customersCount);
            Customer customer = customers.get(customerId);

            int carId = super.getRandom().nextInt(carsCount);
            Car car = cars.get(carId);

            double discount = getDiscount();

            this.em.merge(customer);
            this.em.merge(car);

            Sale sale = new Sale(customer, car, discount);
            this.saleRepository.save(sale);
        }
    }

    @Override
    public Set<SaleQuery6Dto> getAll() {
        List<Sale> sales = this.saleRepository.findAll();
        Set<SaleQuery6Dto> saleDtos = new HashSet<>();
        for (Sale sale : sales) {
            SaleQuery6Dto saleDto = super.getModelMapper().map(sale, SaleQuery6Dto.class);
            Car car = sale.getCar();
            CarQuery6Dto carDto = super.getModelMapper().map(car, CarQuery6Dto.class);
            saleDto.setCar(carDto);

            saleDto.setCustomerName(sale.getCustomer().getName());
            saleDto.setDiscount(sale.getDiscount());
            BigDecimal priceWithoutDiscount = getCarPriceWithoutDiscount(sale);
            saleDto.setPriceWithoutDiscount(priceWithoutDiscount);
            BigDecimal discountAmount = priceWithoutDiscount.multiply(BigDecimal.valueOf(saleDto.getDiscount()));
            discountAmount = discountAmount.divide(BigDecimal.valueOf(100));
            BigDecimal discountedPrice = priceWithoutDiscount.subtract(discountAmount);
            saleDto.setPrice(discountedPrice);

            saleDtos.add(saleDto);

        }
        return saleDtos;
    }

    private BigDecimal getCarPriceWithoutDiscount(Sale sale) {
        List<Double> partPrices = sale.getCar().getParts().stream().map(p -> p.getPrice().doubleValue()).collect(Collectors.toList());
        BigDecimal total = new BigDecimal(0);
        for (Double partPrice : partPrices) {
            total = total.add(BigDecimal.valueOf(partPrice));
        }
        return total;
    }

    private double getDiscount() {
        List<Double> possibleDiscounts = new ArrayList<>();
        possibleDiscounts.add(0.0);
        possibleDiscounts.add(5.0);
        possibleDiscounts.add(10.0);
        possibleDiscounts.add(15.0);
        possibleDiscounts.add(20.0);
        possibleDiscounts.add(30.0);
        possibleDiscounts.add(40.0);
        possibleDiscounts.add(50.0);

        return possibleDiscounts.get(super.getRandom().nextInt(Constants.NUMBER_OF_DISCOUNTS));
    }
}
