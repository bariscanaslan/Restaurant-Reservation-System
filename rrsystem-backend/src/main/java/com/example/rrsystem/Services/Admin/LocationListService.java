package com.example.rrsystem.Services.Admin;

import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Repositories.Admin.Location.LocationListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationListService {

    private final LocationListRepository locationListRepository;

    public LocationListService(LocationListRepository locationListRepository) {
        this.locationListRepository = locationListRepository;
    }

    public List<Map<String, Object>> getLocationListWithRestaurantCount() {
        List<Object[]> results = locationListRepository.findAllLocationsWithRestaurantCount();

        List<Map<String, Object>> locations = new ArrayList<>();
        for (Object[] row : results) {
            Location location = (Location) row[0];
            Long restaurantCount = (Long) row[1];

            Map<String, Object> locationData = new HashMap<>();
            locationData.put("id", location.getId());
            locationData.put("countryName", location.getCountryName());
            locationData.put("cityName", location.getCityName());
            locationData.put("locationActiveness", location.getLocationActiveness());
            locationData.put("locationCreation", location.getLocationCreation());
            locationData.put("locationDeletion", location.getLocationDeletion());
            locationData.put("restaurantCount", restaurantCount != null ? restaurantCount.intValue() : 0);

            locations.add(locationData);
        }

        return locations;
    }
}
