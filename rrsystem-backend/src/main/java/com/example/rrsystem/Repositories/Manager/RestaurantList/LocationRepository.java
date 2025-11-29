package com.example.rrsystem.Repositories.Manager.RestaurantList;

import com.example.rrsystem.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
    @Query("SELECT l FROM Location l WHERE l.locationActiveness = 1 ORDER BY l.countryName, l.cityName ASC")
    List<Location> findByLocationActiveness();
}
