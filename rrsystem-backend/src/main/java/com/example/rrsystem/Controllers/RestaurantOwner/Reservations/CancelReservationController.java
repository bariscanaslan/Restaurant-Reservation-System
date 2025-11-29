package com.example.rrsystem.Controllers.RestaurantOwner.Reservations;

import com.example.rrsystem.Services.RestaurantOwner.Reservations.CancelReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-owner/reservations")
public class CancelReservationController {

    private final CancelReservationService cancelReservationService;

    public CancelReservationController(CancelReservationService cancelReservationService) {
        this.cancelReservationService = cancelReservationService;
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable("id") Long reservationId) {
        boolean result = cancelReservationService.cancelReservation(reservationId);
        if (result) {
            return ResponseEntity.ok("Reservation cancelled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel reservation.");
        }
    }
}
