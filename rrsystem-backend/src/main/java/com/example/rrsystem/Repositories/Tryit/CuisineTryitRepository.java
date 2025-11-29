package com.example.rrsystem.Repositories.Tryit;

import com.example.rrsystem.Entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineTryitRepository extends JpaRepository<Cuisine, Long>{
    @Query("SELECT c FROM Cuisine c WHERE c.cuisineActiveness = 1 ORDER BY c.cuisineName ASC")
    List<Cuisine> findByCuisineActiveness();
}
