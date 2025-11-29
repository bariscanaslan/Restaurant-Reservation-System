package com.example.rrsystem.Controllers.Reservation;

import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.Reservation.SessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/external-customer")
public class QuerySessionsController {

    private final SessionInfoRepository sessionInfoRepository;

    @Autowired
    public QuerySessionsController(SessionInfoRepository sessionInfoRepository) {
        this.sessionInfoRepository = sessionInfoRepository;
    }

    @GetMapping("/sessions")
    public List<SessionInfo> getSessionsByDate(
            @RequestParam("restaurantId") Long restaurantId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return sessionInfoRepository.findActiveSessionsByRestaurantAndDate(restaurantId, date);
    }
}
