package com.example.rrsystem.Controllers.Reservation;
import com.example.rrsystem.Entities.ReservationInfo;
import com.example.rrsystem.Entities.ReservationRequest;
import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.Reservation.SessionInfoRepository;
import com.example.rrsystem.Repositories.Reservation.TableInfoRepository;
import com.example.rrsystem.Services.Mail.EmailService;
import com.example.rrsystem.Services.Reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external-customer")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private SessionInfoRepository sessionRepository;


    @GetMapping("/available-tables")
    public List<TableInfo> getAvailableTables(
            @RequestParam Long restaurantId,
            @RequestParam int peopleNo
    ) {
        List<TableInfo> availableTables = tableInfoRepository.findByRestaurantIdAndTableAvailableAndTableCapacityGreaterThanEqual(
                restaurantId, 1, peopleNo);

        return availableTables;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
            @RequestBody ReservationInfo reservationInfo
    ) {
        return reservationService.createReservation(reservationInfo);
    }
}