package com.example.rrsystem.Services.Admin;

import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Repositories.Admin.Restaurant.RestaurantListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantListService {
    private final RestaurantListRepository restaurantListRepository;

    public RestaurantListService(RestaurantListRepository restaurantListRepository) {
        this.restaurantListRepository = restaurantListRepository;
    }

    public List<RestaurantInfo> getAllRestaurants() {
        return restaurantListRepository.findAllRestaurants();
    }
}
