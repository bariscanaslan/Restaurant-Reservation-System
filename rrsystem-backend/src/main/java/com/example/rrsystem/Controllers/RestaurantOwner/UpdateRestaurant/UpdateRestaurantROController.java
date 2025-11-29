package com.example.rrsystem.Controllers.RestaurantOwner.UpdateRestaurant;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.UpdateRestaurant.UpdateRestaurantRORepository;
import com.example.rrsystem.Security.JwtUtil;
import com.example.rrsystem.Services.RestaurantOwner.UpdateRestaurant.UpdateRestaurantROService;
import com.example.rrsystem.Repositories.RestaurantOwner.CustomerSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant-owner")
public class UpdateRestaurantROController {

    private final JwtUtil jwtUtil;
    private final UpdateRestaurantROService updateRestaurantROService;
    private final UpdateRestaurantRORepository updateRestaurantRORepository;
    private final CustomerSessionRepository customerSessionRepository;

    public UpdateRestaurantROController(UpdateRestaurantRORepository updateRestaurantRORepository, CustomerSessionRepository customerSessionRepository, UpdateRestaurantROService updateRestaurantROService, JwtUtil jwtUtil) {
        this.updateRestaurantROService = updateRestaurantROService;
        this.customerSessionRepository = customerSessionRepository;
        this.updateRestaurantRORepository = updateRestaurantRORepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/cuisine-select")
    public List<Cuisine> getAllCuisines() {
        return updateRestaurantROService.getAllCuisines();
    }
    @GetMapping("/location-select")
    public List<Location> getAllLocations() {
        return updateRestaurantROService.getAllLocations();
    }

    @GetMapping("/restaurant-select")
    public ResponseEntity<?> getRestaurant(
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Unauthorized access"));
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo restaurant = customer.getRestaurant();

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Restaurant not found for this customer"));
        }

        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/update-restaurant")
    public ResponseEntity<?> updateRestaurant(
            @CookieValue(value = "jwt", required = false) String jwtToken,
            @RequestBody RestaurantInfo updatedRestaurant
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Unauthorized access"));
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);
        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo existingRestaurant = customer.getRestaurant();

        if (existingRestaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "No restaurant found for this customer"));
        }

        existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
        existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
        existingRestaurant.setLocation(updatedRestaurant.getLocation());
        existingRestaurant.setRestaurantOwner(updatedRestaurant.getRestaurantOwner());
        existingRestaurant.setRestaurantWebsite(updatedRestaurant.getRestaurantWebsite());
        existingRestaurant.setRestaurantEmail(updatedRestaurant.getRestaurantEmail());
        existingRestaurant.setRestaurantPhone(updatedRestaurant.getRestaurantPhone());
        existingRestaurant.setHoursOfOperation(updatedRestaurant.getHoursOfOperation());
        existingRestaurant.setDressCode(updatedRestaurant.getDressCode());
        existingRestaurant.setParkingDetails(updatedRestaurant.getParkingDetails());

        updateRestaurantRORepository.save(existingRestaurant);

        return ResponseEntity.ok(Collections.singletonMap("message", "Restaurant updated successfully"));
    }



}
