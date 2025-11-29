package com.example.rrsystem.Controllers.Manager.RestaurantList;

import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Services.Admin.RestaurantListService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/manager/restaurants")
public class ManagerRestaurantListController {

    private final RestaurantListService restaurantListService;

    public ManagerRestaurantListController(RestaurantListService restaurantListService) {
        this.restaurantListService = restaurantListService;
    }

    @GetMapping
    public List<RestaurantInfo> getRestaurants() {
        return restaurantListService.getAllRestaurants();
    }
}
