package com.example.rrsystem.Controllers.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.DeleteSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant-owner/sessions")
public class DeleteSessionController {

    @Autowired
    DeleteSessionRepository deleteSessionRepository;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {

        SessionInfo sessionInfo = deleteSessionRepository.findById(id).orElse(null);
        if (sessionInfo != null) {
            try {
                deleteSessionRepository.delete(sessionInfo);
                return ResponseEntity.ok().body("{\"success\": true}");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity
                        .status(409)
                        .body("{\"success\": false, \"message\": \"This session cannot be deleted because it has existing reservations. Please cancel or remove them first.\"}");
            } catch (Exception e) {
                return ResponseEntity
                        .status(500)
                        .body("{\"success\": false, \"message\": \"An unexpected error occurred while deleting the session.\"}");
            }
        } else {
            return ResponseEntity
                    .status(404)
                    .body("{\"success\": false, \"message\": \"Session not found\"}");
        }
    }
}
