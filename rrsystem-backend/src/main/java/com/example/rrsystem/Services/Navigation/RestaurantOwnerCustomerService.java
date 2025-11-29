package com.example.rrsystem.Services.Navigation;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Repositories.Navigation.RestaurantOwnerCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RestaurantOwnerCustomerService {

    @Autowired
    private RestaurantOwnerCustomerRepository restaurantOwnerCustomerRepository;

    public Optional<Map<String, Object>> getCustomerInfoByUserId(Long userId) {
        return restaurantOwnerCustomerRepository.findByUserId(userId);
    }
}