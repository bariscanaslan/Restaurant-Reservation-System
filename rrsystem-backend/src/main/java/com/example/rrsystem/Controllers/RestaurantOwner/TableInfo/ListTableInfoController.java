package com.example.rrsystem.Controllers.RestaurantOwner.TableInfo;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.RestaurantInfo;
import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CustomerSessionRepository;
import com.example.rrsystem.Repositories.RestaurantOwner.TableInfo.ListTableInfoRepository;
import com.example.rrsystem.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant-owner/tables")
public class ListTableInfoController {

    private final ListTableInfoRepository listTableInfoRepository;
    private final CustomerSessionRepository customerSessionRepository;
    private final JwtUtil jwtUtil;

    public ListTableInfoController(
            ListTableInfoRepository listTableInfoRepository,
            CustomerSessionRepository customerSessionRepository,
            JwtUtil jwtUtil
    ) {
        this.listTableInfoRepository = listTableInfoRepository;
        this.customerSessionRepository = customerSessionRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/all-tables")
    public ResponseEntity<?> getAllTables(
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        Long customerId = jwtUtil.extractCustomerId(jwtToken);

        Customer customer = customerSessionRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RestaurantInfo restaurant = customer.getRestaurant();

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found for this customer");
        }

        List<TableInfo> tables = listTableInfoRepository.findByRestaurantId(restaurant.getId());
        return ResponseEntity.ok(tables);
    }
}
