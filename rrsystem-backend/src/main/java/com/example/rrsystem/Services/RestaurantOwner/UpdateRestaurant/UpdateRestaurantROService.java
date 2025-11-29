package com.example.rrsystem.Services.RestaurantOwner.UpdateRestaurant;

import com.example.rrsystem.Entities.Cuisine;
import com.example.rrsystem.Entities.Location;
import com.example.rrsystem.Repositories.RestaurantOwner.UpdateRestaurant.CuisineRORepository;
import com.example.rrsystem.Repositories.RestaurantOwner.UpdateRestaurant.LocationRORepository;
import com.example.rrsystem.Repositories.RestaurantOwner.UpdateRestaurant.UpdateRestaurantRORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateRestaurantROService {

    @Autowired
    private CuisineRORepository cuisineRORepository;

    @Autowired
    private LocationRORepository locationRORepository;

    @Autowired
    private UpdateRestaurantRORepository updateRestaurantRORepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineRORepository.findByCuisineActiveness();
    }

    public List<Location> getAllLocations() {
        return locationRORepository.findByLocationActiveness();
    }

}
