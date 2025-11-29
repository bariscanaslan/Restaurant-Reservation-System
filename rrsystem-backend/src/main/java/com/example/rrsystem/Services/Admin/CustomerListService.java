package com.example.rrsystem.Services.Admin;
import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Repositories.Admin.Customer.CustomerListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerListService {

    private final CustomerListRepository customerListRepository;

    public CustomerListService(CustomerListRepository customerListRepository) {
        this.customerListRepository = customerListRepository;
    }

    public List<Customer> getCustomerList() {
        return customerListRepository.findAllCustomers();
    }
}
