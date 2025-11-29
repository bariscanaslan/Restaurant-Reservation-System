package com.example.rrsystem.Repositories.Navigation;

import com.example.rrsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;

public interface RestaurantOwnerCustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT new map(c.id as id, c.user.id as userId, c.user.username as username, " +
            "c.user.name as name, c.user.surname as surname, " +
            "c.user.email as email, c.user.phone as phone, " +
            "c.user.photo as photo, c.user.userType as userType, " +
            "c.restaurant.id as restaurantId) " +
            "FROM Customer c WHERE c.user.id = :userId")
    Optional<Map<String, Object>> findByUserId(@Param("userId") Long userId);
}