package com.example.rrsystem.Controllers.Admin.Plan;

import com.example.rrsystem.Services.Admin.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/prices")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PutMapping("/update-price/{id}")
    public ResponseEntity<String> updatePlanPrice(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        int newPrice = requestBody.get("price");
        boolean isUpdated = planService.updatePlanPrice(id, newPrice);
        if (isUpdated) {
            return new ResponseEntity<>("Plan price updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Plan not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-quota/{id}")
    public ResponseEntity<String> updatePlanQuota(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        int newQuota = requestBody.get("quota");
        boolean isUpdated = planService.updatePlanQuota(id, newQuota);
        if (isUpdated) {
            return new ResponseEntity<>("Plan quota updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Plan not found", HttpStatus.NOT_FOUND);
        }
    }
}

