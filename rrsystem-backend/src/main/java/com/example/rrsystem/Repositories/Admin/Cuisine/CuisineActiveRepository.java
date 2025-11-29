package com.example.rrsystem.Repositories.Admin.Cuisine;

import com.example.rrsystem.Entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineActiveRepository extends JpaRepository<Cuisine, Long> {
}
