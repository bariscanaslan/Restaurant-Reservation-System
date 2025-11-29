package com.example.rrsystem.Controllers.RestaurantOwner.YourPlan;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Security.CustomerRepository;
import com.example.rrsystem.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-owner/your-plan")
public class YourPlanController {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public YourPlanController(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/customer-plan")
    ResponseEntity<?> getCustomerPlan(
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Customer not found");
        }

        return ResponseEntity.ok(customer);
    }

}
