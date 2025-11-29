package com.example.rrsystem.Repositories.Admin.Location;

import com.example.rrsystem.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDeleteRepository extends JpaRepository<Location, Long> {
}
