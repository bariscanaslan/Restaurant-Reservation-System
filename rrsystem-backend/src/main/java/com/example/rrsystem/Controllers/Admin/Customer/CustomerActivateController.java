package com.example.rrsystem.Controllers.Admin.Customer;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Admin.Customer.CustomerDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/customers")
public class CustomerActivateController {

    @Autowired
    private CustomerDeleteRepository customerDeleteRepository;

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> deactivateCustomer(@PathVariable Long id) {
        UserInfo customer = customerDeleteRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setActiveness(1);
            customer.setDeletedAt(LocalDateTime.now());
            customerDeleteRepository.save(customer);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
