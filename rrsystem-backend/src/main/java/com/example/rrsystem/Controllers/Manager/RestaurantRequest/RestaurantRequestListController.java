package com.example.rrsystem.Controllers.Manager.RestaurantRequest;

import com.example.rrsystem.Entities.RestaurantRequest;
import com.example.rrsystem.Services.Manager.RestaurantRequestListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/manager/restaurant-requests")
public class RestaurantRequestListController {

    private final RestaurantRequestListService restaurantRequestListService;

    public RestaurantRequestListController(RestaurantRequestListService restaurantRequestListService) {
        this.restaurantRequestListService = restaurantRequestListService;
    }

    @GetMapping
    public List<RestaurantRequest> getRestaurantRequests() {
        return restaurantRequestListService.getAllRequests();
    }
}