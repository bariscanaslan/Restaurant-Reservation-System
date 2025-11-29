package com.example.rrsystem.Repositories.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCreateRepository extends JpaRepository<Customer, Long> {
}
