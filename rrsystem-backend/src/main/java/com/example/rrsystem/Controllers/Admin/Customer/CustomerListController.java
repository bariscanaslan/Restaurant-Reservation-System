package com.example.rrsystem.Controllers.Admin.Customer;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Services.Admin.CustomerListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/customers")
public class CustomerListController {
    private final CustomerListService customerListService;

    public CustomerListController(CustomerListService customerListService) {
        this.customerListService = customerListService;
    }

    @GetMapping
    public List<Customer> getCustomerList() {
        return customerListService.getCustomerList();
    }
}
