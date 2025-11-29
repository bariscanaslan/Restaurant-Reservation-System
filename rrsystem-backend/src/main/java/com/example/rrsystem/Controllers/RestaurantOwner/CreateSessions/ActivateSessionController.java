package com.example.rrsystem.Controllers.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.ActiveSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/restaurant-owner/sessions")
public class ActivateSessionController {

    @Autowired
    ActiveSessionRepository activeSessionRepository;

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateSession(@PathVariable Long id) {

        SessionInfo sessionInfo = activeSessionRepository.findById(id).orElse(null);
        if (sessionInfo != null) {
            sessionInfo.setSessionActiveness(1);
            sessionInfo.setSessionDeletion(LocalDateTime.now());
            activeSessionRepository.save(sessionInfo);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
