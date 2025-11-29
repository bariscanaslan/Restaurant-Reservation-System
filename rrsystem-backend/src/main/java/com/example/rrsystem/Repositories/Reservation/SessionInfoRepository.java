package com.example.rrsystem.Repositories.Reservation;

import com.example.rrsystem.Entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SessionInfoRepository extends JpaRepository<SessionInfo, Long> {

    @Query("SELECT s FROM SessionInfo s " +
            "WHERE s.restaurant.id = :restaurantId " +
            "AND s.sessionDate = :sessionDate " +
            "AND s.sessionActiveness = 1" +
            "ORDER BY s.sessionStart ASC")
    List<SessionInfo> findActiveSessionsByRestaurantAndDate(
            @Param("restaurantId") Long restaurantId,
            @Param("sessionDate") LocalDate sessionDate);
}
