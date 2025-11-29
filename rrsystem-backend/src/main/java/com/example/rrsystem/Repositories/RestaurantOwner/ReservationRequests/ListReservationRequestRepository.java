package com.example.rrsystem.Repositories.RestaurantOwner.ReservationRequests;

import com.example.rrsystem.Entities.ReservationRequest;
import com.example.rrsystem.Entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    @Query("SELECT r FROM ReservationRequest r WHERE r.restaurant.id = :restaurantId ORDER BY r.reservationRequestDate DESC, r.reservationRequestTime DESC")
    List<ReservationRequest> findByRestaurantId(Long restaurantId);
}
