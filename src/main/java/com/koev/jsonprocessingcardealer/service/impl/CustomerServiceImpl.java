package com.koev.jsonprocessingcardealer.service.impl;

import com.koev.jsonprocessingcardealer.domain.dto.Dto;
import com.koev.jsonprocessingcardealer.domain.dto._01_ordered_customers.CustomerDto;
import com.koev.jsonprocessingcardealer.domain.dto._05_total_sales_by_customer.CustomerQuery5Dto;
import com.koev.jsonprocessingcardealer.domain.entity.Customer;
import com.koev.jsonprocessingcardealer.domain.entity.Part;
import com.koev.jsonprocessingcardealer.domain.entity.Sale;
import com.koev.jsonprocessingcardealer.repository.CustomerRepository;
import com.koev.jsonprocessingcardealer.repository.SaleRepository;
import com.koev.jsonprocessingcardealer.service.CustomerService;
import com.koev.jsonprocessingcardealer.service.SeedableService;
import com.koev.jsonprocessingcardealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends SeedableService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    protected CustomerServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, Random random,
                                  CustomerRepository customerRepository, SaleRepository saleRepository) {
        super(modelMapper, random, validatorUtil);
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seed(Dto[] dtos) {
        for (Dto dto : dtos) {

            Customer customer = super.getModelMapper().map(dto, Customer.class);
            this.customerRepository.save(customer);
        }
    }

    @Override
    public Set<CustomerDto> getOrderedCustomers() {
        Set<Customer> customers = this.customerRepository.orderedCustomers();
        Set<CustomerDto> customerDtos = new LinkedHashSet<>();

        for (Customer customer : customers) {
            Set<Sale> sales = this.saleRepository.findAllByCustomer(customer);
            customer.setSales(sales);
            CustomerDto customerDto = super.getModelMapper().map(customer, CustomerDto.class);
            customerDtos.add(customerDto);
        }

        return customerDtos;
    }

    @Override
    public List<CustomerQuery5Dto> allCustomersWhoBoughtACar() {
        Set<Customer> customers = this.customerRepository.findAllWhoHaveBoughtAtLeast1Car();
        List<CustomerQuery5Dto> customerQuery5Dtos = new LinkedList<>();

        for (Customer customer : customers) {
            CustomerQuery5Dto dto = super.getModelMapper().map(customer, CustomerQuery5Dto.class);

            dto.setFullName(customer.getName());
            dto.setBoughtCars(customer.getSales().size());
            dto.setSpentMoney(calculatespentMoney(customer));
            customerQuery5Dtos.add(dto);
        }

        customerQuery5Dtos = customerQuery5Dtos.stream().sorted((c2, c1) -> {
                    if (!c1.getSpentMoney().equals(c2.getSpentMoney())) {
                        int money1 = c1.getSpentMoney().intValue();
                        int money2 = c2.getSpentMoney().intValue();
                        return money1 - money2;
                    } else {
                        return c1.getBoughtCars() - c2.getBoughtCars();
                    }
                }
        ).collect(Collectors.toList());
        return customerQuery5Dtos;
    }

    private BigDecimal calculatespentMoney(Customer customer) {

        BigDecimal total = new BigDecimal(0);
        for (Sale sale : customer.getSales()) {
            Set<BigDecimal> prices = sale.getCar().getParts().stream().map(Part::getPrice).collect(Collectors.toSet());
            BigDecimal currentSum = new BigDecimal(0);
            for (BigDecimal price : prices) {
                currentSum = currentSum.add(price);
            }
            total = total.add(currentSum);
        }
        return total;
    }
}
