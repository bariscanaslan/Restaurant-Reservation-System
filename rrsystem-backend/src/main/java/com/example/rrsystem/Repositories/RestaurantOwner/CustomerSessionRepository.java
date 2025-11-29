package com.example.rrsystem.Repositories.RestaurantOwner;

import com.example.rrsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSessionRepository extends JpaRepository<Customer, Long> {
}