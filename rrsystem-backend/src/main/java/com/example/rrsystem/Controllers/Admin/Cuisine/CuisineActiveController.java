package com.example.rrsystem.Controllers.Admin.Cuisine;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Repositories.Admin.Cuisine.CuisineActiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/cuisines")
public class CuisineActiveController {

    @Autowired
    CuisineActiveRepository cuisineActiveRepository;

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        Cuisine cuisine = cuisineActiveRepository.findById(id).orElse(null);
        if (cuisine != null) {
            cuisine.setCuisineActiveness(1);
            cuisine.setCuisineDeletion(LocalDateTime.now());
            cuisineActiveRepository.save(cuisine);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }

}
