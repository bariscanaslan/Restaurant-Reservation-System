package com.example.rrsystem.Controllers.Admin.Restaurant;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Services.Admin.RestaurantListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantListController {

    private final RestaurantListService restaurantListService;

    public AdminRestaurantListController(RestaurantListService restaurantListService) {
        this.restaurantListService = restaurantListService;
    }

    @GetMapping
    public List<RestaurantInfo> getRestaurants() {
        return restaurantListService.getAllRestaurants();
    }
}
