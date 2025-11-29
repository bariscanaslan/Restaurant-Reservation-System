package com.example.rrsystem.Repositories.RestaurantOwner.Reservations;

import com.example.rrsystem.Entities.ReservationInfo;
import com.example.rrsystem.Entities.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListViewRepository extends JpaRepository<ReservationInfo, Long> {
    @Query("SELECT r FROM ReservationInfo r WHERE r.restaurant.id = :restaurantId ORDER BY r.session.sessionDate DESC, r.session.sessionStart DESC")
    List<ReservationInfo> findByRestaurantId(Long restaurantId);
}
