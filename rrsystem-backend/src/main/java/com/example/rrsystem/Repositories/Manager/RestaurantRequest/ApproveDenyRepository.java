package com.example.rrsystem.Repositories.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.RestaurantRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApproveDenyRepository extends JpaRepository<RestaurantRequest, Long> {
}
