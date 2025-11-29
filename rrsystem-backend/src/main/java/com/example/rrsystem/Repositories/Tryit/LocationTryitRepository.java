package com.example.rrsystem.Repositories.Tryit;

import com.example.rrsystem.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationTryitRepository extends JpaRepository<Location, Long>{
    @Query("SELECT l FROM Location l WHERE l.locationActiveness = 1 ORDER BY l.countryName, l.cityName ASC")
    List<Location> findByLocationActiveness();
}
