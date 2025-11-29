package com.example.rrsystem.Controllers.Admin.Location;

import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Repositories.Admin.Location.AddLocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/locations")
public class AddLocationController {

    private final AddLocationRepository addLocationRepository;

    public AddLocationController(AddLocationRepository addLocationRepository) {
        this.addLocationRepository = addLocationRepository;
    }

    @PostMapping("add-location")
    public ResponseEntity<?> addLocation(@RequestBody Location location) {
        if (addLocationRepository.findByCityName(location.getCityName()).isPresent()) {
            return ResponseEntity.badRequest().body("City name already exists!");
        }

        location.setLocationActiveness(1);
        location.setLocationCreation(LocalDateTime.now());

        Location savedLocation = addLocationRepository.save(location);
        return ResponseEntity.ok(savedLocation);
    }
}
