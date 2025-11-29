package com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface AddSessionRepository extends JpaRepository<SessionInfo, Long> {
    boolean existsByRestaurantAndSessionDateAndSessionStart(
            RestaurantInfo restaurant, Date sessionDate, LocalTime sessionStart);
}