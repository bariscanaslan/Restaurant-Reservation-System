package com.example.rrsystem.Services.Manager;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Repositories.Manager.RestaurantList.UpdateRestaurantRepository;
import com.example.rrsystem.Repositories.Manager.RestaurantList.CuisineRepository;
import com.example.rrsystem.Repositories.Manager.RestaurantList.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpdateRestaurantService {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UpdateRestaurantRepository restaurantRepository;

    // Get all cuisines
    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findByCuisineActiveness();
    }

    // Get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findByLocationActiveness();
    }

    // Get all restaurants
    public List<RestaurantInfo> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantInfo updateRestaurant(Long restaurantId, RestaurantInfo updatedRestaurantInfo) {
        RestaurantInfo existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // Ensure the cuisine and location are set by their IDs, not the objects themselves.
        Cuisine cuisine = cuisineRepository.findById(updatedRestaurantInfo.getCuisine().getId())
                .orElseThrow(() -> new RuntimeException("Cuisine not found"));
        Location location = locationRepository.findById(updatedRestaurantInfo.getLocation().getId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        // Set values for restaurant
        existingRestaurant.setRestaurantName(updatedRestaurantInfo.getRestaurantName());
        existingRestaurant.setCuisine(cuisine);
        existingRestaurant.setLocation(location);
        existingRestaurant.setRestaurantOwner(updatedRestaurantInfo.getRestaurantOwner());
        existingRestaurant.setRestaurantWebsite(updatedRestaurantInfo.getRestaurantWebsite());
        existingRestaurant.setRestaurantEmail(updatedRestaurantInfo.getRestaurantEmail());
        existingRestaurant.setRestaurantPhone(updatedRestaurantInfo.getRestaurantPhone());
        existingRestaurant.setHoursOfOperation(updatedRestaurantInfo.getHoursOfOperation());
        existingRestaurant.setDressCode(updatedRestaurantInfo.getDressCode());
        existingRestaurant.setParkingDetails(updatedRestaurantInfo.getParkingDetails());
        existingRestaurant.setRestaurantCreation(LocalDateTime.now());

        return restaurantRepository.save(existingRestaurant);
    }

}
