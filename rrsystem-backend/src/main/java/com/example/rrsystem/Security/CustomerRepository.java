package com.example.rrsystem.Security;

import com.example.rrsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserId(Long userId);
}
