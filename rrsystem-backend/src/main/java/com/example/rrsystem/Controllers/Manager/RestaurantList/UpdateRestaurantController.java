package com.example.rrsystem.Controllers.Manager.RestaurantList;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Services.Manager.UpdateRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/manager")
public class UpdateRestaurantController {

    @Autowired
    private UpdateRestaurantService updateRestaurantService;

    @GetMapping("/cuisine-select")
    public List<Cuisine> getAllCuisines() {
        return updateRestaurantService.getAllCuisines();
    }
    @GetMapping("/location-select")
    public List<Location> getAllLocations() {
        return updateRestaurantService.getAllLocations();
    }

    @GetMapping("/restaurant-select")
    public List<RestaurantInfo> getAllRestaurants() {
        return updateRestaurantService.getAllRestaurants();
    }

    @PutMapping("/update-restaurant/{restaurantId}")
    public ResponseEntity<Object> updateRestaurant(@PathVariable("restaurantId") Long restaurantId, @RequestBody RestaurantInfo updatedRestaurantInfo) {
        try {
            RestaurantInfo updatedRestaurant = updateRestaurantService.updateRestaurant(restaurantId, updatedRestaurantInfo);
            return ResponseEntity.ok(updatedRestaurant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update restaurant: " + e.getMessage());
        }
    }

}
