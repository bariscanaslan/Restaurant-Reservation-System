package com.example.rrsystem.Controllers.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.AddSessionRepository;
import com.example.rrsystem.Repositories.RestaurantOwner.CustomerSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rrsystem.Security.JwtUtil;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api/restaurant-owner/sessions")
public class AddSessionController {

    private final JwtUtil jwtUtil;
    private final AddSessionRepository addSessionRepository;
    private final CustomerSessionRepository customerSessionRepository;

    public AddSessionController(AddSessionRepository addSessionRepository, CustomerSessionRepository customerSessionRepository, JwtUtil jwtUtil) {
        this.addSessionRepository = addSessionRepository;
        this.customerSessionRepository = customerSessionRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/add-session")
    public ResponseEntity<?> addSession(
            @RequestBody SessionInfo sessionInfo,
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Unauthorized access"));
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo restaurant = customer.getRestaurant();

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Restaurant not found for this customer"));
        }

        boolean isSessionExist = addSessionRepository.existsByRestaurantAndSessionDateAndSessionStart(
                restaurant, sessionInfo.getSessionDate(), sessionInfo.getSessionStart());

        if (isSessionExist) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Session time is already exist"));
        }

        sessionInfo.setSessionActiveness(1);
        sessionInfo.setSessionCreation(LocalDateTime.now());
        sessionInfo.setRestaurant(restaurant);

        SessionInfo savedSessionInfo = addSessionRepository.save(sessionInfo);
        return ResponseEntity.ok(savedSessionInfo);
    }
}
