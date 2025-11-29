package com.example.rrsystem.Controllers.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.RestaurantRequest;
import com.example.rrsystem.Services.Manager.ApproveDenyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ApproveDenyController {

    private final ApproveDenyService approveDenyService;

    public ApproveDenyController(ApproveDenyService approveDenyService) {
        this.approveDenyService = approveDenyService;
    }

    @GetMapping("/restaurant-request/{id}")
    public ResponseEntity<RestaurantRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(approveDenyService.getRequestById(id));
    }

    @PostMapping("/restaurant-request/{id}/approve")
    public ResponseEntity<String> approveRequest(@PathVariable Long id, @RequestBody RestaurantRequest updatedRequest, @RequestParam String username) {
        approveDenyService.approveRequest(id, updatedRequest, username);
        return ResponseEntity.ok("Request approved.");
    }

    @PostMapping("/restaurant-request/{id}/deny")
    public ResponseEntity<String> denyRequest(@PathVariable Long id, @RequestBody String reason) {
        reason = reason.replaceAll("^\"|\"$", "");
        approveDenyService.denyRequest(id, reason);
        return ResponseEntity.ok("Request denied.");
    }
}
