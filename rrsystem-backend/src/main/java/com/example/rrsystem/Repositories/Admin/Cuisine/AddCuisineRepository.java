package com.example.rrsystem.Repositories.Admin.Cuisine;

import com.example.rrsystem.Entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddCuisineRepository extends JpaRepository<Cuisine, Long> {
    Optional<Cuisine> findByCuisineName(String cuisineName);
}
