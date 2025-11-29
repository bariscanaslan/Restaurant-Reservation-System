package com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSessionsRepository extends JpaRepository<SessionInfo, Long> {
    @Query("SELECT m FROM SessionInfo m WHERE m.restaurant.id = :restaurantId ORDER BY m.sessionDate DESC, m.sessionStart DESC")
    List<SessionInfo> findByRestaurantId(Long restaurantId);
}
