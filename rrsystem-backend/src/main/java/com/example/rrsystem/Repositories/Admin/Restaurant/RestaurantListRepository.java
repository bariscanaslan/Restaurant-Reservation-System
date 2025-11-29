package com.example.rrsystem.Repositories.Admin.Restaurant;

import com.example.rrsystem.Entities.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantListRepository extends JpaRepository<RestaurantInfo, Long> {
    @Query("SELECT r FROM RestaurantInfo r JOIN FETCH r.cuisine JOIN FETCH r.location LEFT JOIN FETCH r.customer ORDER BY r.restaurantName")
    List<RestaurantInfo> findAllRestaurants();
}
