package com.example.rrsystem.Controllers.Admin.Cuisine;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Repositories.Admin.Cuisine.AddCuisineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/cuisines")
public class AddCuisineController {

    private final AddCuisineRepository addCuisineRepository;

    public AddCuisineController(AddCuisineRepository addCuisineRepository) {
        this.addCuisineRepository = addCuisineRepository;
    }

    @PostMapping("/add-cuisine")
    public ResponseEntity<?> addCuisine(@RequestBody Cuisine cuisine) {

        if (addCuisineRepository.findByCuisineName(cuisine.getCuisineName()).isPresent()) {
            return ResponseEntity.badRequest().body("Cuisine name already exists!");
        }

        cuisine.setCuisineCreation(LocalDateTime.now());
        cuisine.setCuisineActiveness(1);

        Cuisine savedCuisine = addCuisineRepository.save(cuisine);
        return ResponseEntity.ok(savedCuisine);
    }

}
