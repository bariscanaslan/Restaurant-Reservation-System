package com.example.rrsystem.Controllers.Admin.Location;

import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Repositories.Admin.Location.LocationDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/locations")
public class LocationDeleteController {

    @Autowired
    LocationDeleteRepository locationDeleteRepository;

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateCustomer(@PathVariable Long id) {
        Location location = locationDeleteRepository.findById(id).orElse(null);
        if (location != null) {
            location.setLocationActiveness(0);
            location.setLocationDeletion(LocalDateTime.now());
            locationDeleteRepository.save(location);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
