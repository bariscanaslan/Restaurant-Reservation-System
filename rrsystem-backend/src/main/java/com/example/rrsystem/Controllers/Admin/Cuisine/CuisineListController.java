package com.example.rrsystem.Controllers.Admin.Cuisine;

import com.example.rrsystem.Services.Admin.CuisineListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/cuisines")
public class CuisineListController {

    private final CuisineListService cuisineListService;

    public CuisineListController(CuisineListService cuisineListService) {
        this.cuisineListService = cuisineListService;
    }

    @GetMapping
    public List<Map<String, Object>> getCuisineListWithRestaurantCount() {
        return cuisineListService.getCuisineListWithRestaurantCount();
    }
}
