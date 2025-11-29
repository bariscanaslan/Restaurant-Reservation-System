package com.example.rrsystem.Repositories.RestaurantOwner.YourPlan;

import com.example.rrsystem.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YourPlanRepository extends JpaRepository<Payment, Long> {
}
