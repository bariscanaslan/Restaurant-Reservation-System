package com.example.rrsystem.Controllers.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.DeactivateSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/restaurant-owner/sessions")
public class DeactivateSessionController {

    @Autowired
    DeactivateSessionRepository deactivateSessionRepository;

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> activateSession(@PathVariable Long id) {

        SessionInfo sessionInfo = deactivateSessionRepository.findById(id).orElse(null);
        if (sessionInfo != null) {
            sessionInfo.setSessionActiveness(0);
            sessionInfo.setSessionDeletion(LocalDateTime.now());
            deactivateSessionRepository.save(sessionInfo);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
