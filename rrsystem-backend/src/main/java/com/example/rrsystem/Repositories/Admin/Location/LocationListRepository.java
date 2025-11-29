package com.example.rrsystem.Repositories.Admin.Location;

import com.example.rrsystem.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationListRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l, COUNT(r.id) as restaurantCount " +
            "FROM Location l LEFT JOIN RestaurantInfo r ON l.id = r.location.id " +
            "GROUP BY l " +
            "ORDER BY l.countryName ASC, l.cityName ASC")
    List<Object[]> findAllLocationsWithRestaurantCount();
}
