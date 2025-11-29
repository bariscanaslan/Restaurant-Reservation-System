package com.example.rrsystem.Repositories.Admin.Location;

import com.example.rrsystem.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddLocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityName(String cityName);
}
