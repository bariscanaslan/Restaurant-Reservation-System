package com.example.rrsystem.Controllers.Admin.Location;

import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Repositories.Admin.Location.LocationActiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/locations")
public class LocationActiveController {

    @Autowired
    LocationActiveRepository locationActiveRepository;
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        Location location = locationActiveRepository.findById(id).orElse(null);
        if (location != null) {
            location.setLocationActiveness(1);
            location.setLocationDeletion(LocalDateTime.now());
            locationActiveRepository.save(location);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }

}
