package com.example.rrsystem.Repositories.Manager.RestaurantList;

import com.example.rrsystem.Entities.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRestaurantRepository extends JpaRepository<RestaurantInfo, Long>{
}
