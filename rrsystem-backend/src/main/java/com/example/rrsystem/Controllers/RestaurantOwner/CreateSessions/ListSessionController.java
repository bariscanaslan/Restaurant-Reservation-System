package com.example.rrsystem.Controllers.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CustomerSessionRepository;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.ListSessionsRepository;
import com.example.rrsystem.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant-owner/sessions")
public class ListSessionController {

    private final ListSessionsRepository listSessionsRepository;
    private final CustomerSessionRepository customerSessionRepository;
    private final JwtUtil jwtUtil;

    public ListSessionController(
            ListSessionsRepository listSessionsRepository,
            CustomerSessionRepository customerSessionRepository,
            JwtUtil jwtUtil
    ) {
        this.listSessionsRepository = listSessionsRepository;
        this.customerSessionRepository = customerSessionRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/all-sessions")
    public ResponseEntity<?> getAllSessions(
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo restaurant = customer.getRestaurant();

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found for this customer");
        }

        List<SessionInfo> sessions = listSessionsRepository.findByRestaurantId(restaurant.getId());
        return ResponseEntity.ok(sessions);
    }
}

