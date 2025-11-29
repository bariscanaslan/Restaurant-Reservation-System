package com.example.rrsystem.Services.Admin;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Repositories.Admin.Cuisine.CuisineListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CuisineListService {

    private final CuisineListRepository cuisineListRepository;

    public CuisineListService(CuisineListRepository cuisineListRepository) {
        this.cuisineListRepository = cuisineListRepository;
    }

    public List<Map<String, Object>> getCuisineListWithRestaurantCount() {
        List<Object[]> results = cuisineListRepository.findAllCuisinesWithRestaurantCount();

        List<Map<String, Object>> cuisines = new ArrayList<>();
        for (Object[] row : results) {
            Cuisine cuisine = (Cuisine) row[0];
            Long restaurantCount = (Long) row[1];

            Map<String, Object> cuisineData = new HashMap<>();
            cuisineData.put("id", cuisine.getId());
            cuisineData.put("cuisineName", cuisine.getCuisineName());
            cuisineData.put("cuisineActiveness", cuisine.getCuisineActiveness());
            cuisineData.put("cuisineCreation", cuisine.getCuisineCreation());
            cuisineData.put("cuisineDeletion", cuisine.getCuisineDeletion());
            cuisineData.put("restaurantCount", restaurantCount != null ? restaurantCount.intValue() : 0);

            cuisines.add(cuisineData);
        }

        return cuisines;
    }
}

