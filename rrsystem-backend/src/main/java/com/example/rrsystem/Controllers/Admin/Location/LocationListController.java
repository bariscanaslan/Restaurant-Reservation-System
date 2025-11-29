package com.example.rrsystem.Controllers.Admin.Location;

import com.example.rrsystem.Services.Admin.LocationListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/locations")
public class LocationListController {

    private final LocationListService locationListService;

    public LocationListController(LocationListService locationListService) {
        this.locationListService = locationListService;
    }

    @GetMapping
    public List<Map<String, Object>> getLocationListWithRestaurantCount() {
        return locationListService.getLocationListWithRestaurantCount();
    }
}
