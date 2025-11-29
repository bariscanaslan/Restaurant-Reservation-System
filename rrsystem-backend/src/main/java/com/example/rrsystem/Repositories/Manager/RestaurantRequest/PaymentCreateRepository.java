package com.example.rrsystem.Repositories.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCreateRepository extends JpaRepository<Payment, Long> {
}
