package com.example.rrsystem.Repositories.Tryit;

import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.RestaurantRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryitRestaurantRepository extends JpaRepository<RestaurantRequest, Long>{
}
