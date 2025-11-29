package com.example.rrsystem.Services.Tryit;

import com.example.rrsystem.Entities.*;
import com.example.rrsystem.Repositories.Tryit.CuisineTryitRepository;
import com.example.rrsystem.Repositories.Tryit.LocationTryitRepository;
import com.example.rrsystem.Repositories.Tryit.TryitRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TryitService {

    @Autowired
    private CuisineTryitRepository cuisineTryitRepository;

    @Autowired
    private LocationTryitRepository locationTryitRepository;

    @Autowired
    private TryitRestaurantRepository tryitRestaurantRepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineTryitRepository.findByCuisineActiveness();
    }

    public List<Location> getAllLocations() {
        return locationTryitRepository.findByLocationActiveness();
    }

    public ResponseEntity<?> addRestaurantRequest(@RequestBody RestaurantRequest newReservationRequest) {

        Cuisine cuisine = cuisineTryitRepository.findById(newReservationRequest.getCuisine().getId())
                .orElseThrow(() -> new RuntimeException("Cuisine not found"));
        Location location = locationTryitRepository.findById(newReservationRequest.getLocation().getId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        newReservationRequest.setLocation(location);
        newReservationRequest.setCuisine(cuisine);
        newReservationRequest.setRequestSendDate(LocalDateTime.now());

        RestaurantRequest restaurantRequest = tryitRestaurantRepository.save(newReservationRequest);
        return ResponseEntity.ok(restaurantRequest);
    }

}
