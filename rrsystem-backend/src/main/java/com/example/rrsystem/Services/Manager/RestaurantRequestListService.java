package com.example.rrsystem.Services.Manager;

import com.example.rrsystem.Entities.RestaurantRequest;
import com.example.rrsystem.Repositories.Manager.RestaurantRequest.RestaurantRequestListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantRequestListService {
    private final RestaurantRequestListRepository restaurantRequestListRepository;

    public RestaurantRequestListService(RestaurantRequestListRepository restaurantRequestListRepository) {
        this.restaurantRequestListRepository = restaurantRequestListRepository;
    }

    public List<RestaurantRequest> getAllRequests() {
        return restaurantRequestListRepository.findAllRequests();
    }
}