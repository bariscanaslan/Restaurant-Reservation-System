package com.example.rrsystem.Controllers.Navigation;

import com.example.rrsystem.Services.Navigation.RestaurantOwnerCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/navigation-restaurant-owner")
public class NavigationRestaurantOwnerController {

    @Autowired
    private RestaurantOwnerCustomerService restaurantOwnerCustomerService;

    @GetMapping("/{userId}")
    public Optional<Map<String, Object>> getCustomerInfo(@PathVariable Long userId) {
        return restaurantOwnerCustomerService.getCustomerInfoByUserId(userId);
    }
}
