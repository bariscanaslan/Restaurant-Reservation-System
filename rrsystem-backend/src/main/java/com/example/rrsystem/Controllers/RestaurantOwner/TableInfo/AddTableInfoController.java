package com.example.rrsystem.Controllers.RestaurantOwner.TableInfo;


import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CustomerSessionRepository;
import com.example.rrsystem.Repositories.RestaurantOwner.TableInfo.AddTableInfoRepository;
import com.example.rrsystem.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api/restaurant-owner/tables")
public class AddTableInfoController {

    private final JwtUtil jwtUtil;
    private final AddTableInfoRepository addTableInfoRepository;
    private final CustomerSessionRepository customerSessionRepository;

    public AddTableInfoController(AddTableInfoRepository addTableInfoRepository, CustomerSessionRepository customerSessionRepository, JwtUtil jwtUtil) {
        this.addTableInfoRepository = addTableInfoRepository;
        this.customerSessionRepository = customerSessionRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/add-table")
    public ResponseEntity<?> addTable(
            @RequestBody TableInfo tableInfo,
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Unauthorized access"));
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo restaurant = customer.getRestaurant();

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Restaurant not found for this customer"));
        }

        boolean isTableExist = addTableInfoRepository.existsByTableName(
                tableInfo.getTableName());

        if (isTableExist) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Table name is already exist"));
        }

        tableInfo.setTableActiveness(1);
        tableInfo.setTableAvailable(1);
        tableInfo.setTableCreation(LocalDateTime.now());
        tableInfo.setRestaurant(restaurant);

        TableInfo savedTableInfo = addTableInfoRepository.save(tableInfo);
        return ResponseEntity.ok(savedTableInfo);
    }

}
