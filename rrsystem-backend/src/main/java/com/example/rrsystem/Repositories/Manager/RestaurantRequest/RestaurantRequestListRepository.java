// RestaurantRequestRepository.java
package com.example.rrsystem.Repositories.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.RestaurantRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRequestListRepository extends JpaRepository<RestaurantRequest, Long> {
    @Query("SELECT r FROM RestaurantRequest r JOIN FETCH r.cuisine JOIN FETCH r.location ORDER BY r.restaurantName")
    List<RestaurantRequest> findAllRequests();
}