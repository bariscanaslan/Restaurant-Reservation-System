package com.example.rrsystem.Repositories.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantInfoCreateRepository extends JpaRepository<RestaurantInfo, Long> {
}
