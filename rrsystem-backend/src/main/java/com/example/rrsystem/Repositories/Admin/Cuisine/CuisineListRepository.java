package com.example.rrsystem.Repositories.Admin.Cuisine;

import com.example.rrsystem.Entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineListRepository extends JpaRepository<Cuisine, Long> {
    @Query("SELECT c, COUNT(r.id) as restaurantCount " +
            "FROM Cuisine c LEFT JOIN RestaurantInfo r ON c.id = r.cuisine.id " +
            "GROUP BY c " +
            "ORDER BY c.cuisineName ASC")
    List<Object[]> findAllCuisinesWithRestaurantCount();
}