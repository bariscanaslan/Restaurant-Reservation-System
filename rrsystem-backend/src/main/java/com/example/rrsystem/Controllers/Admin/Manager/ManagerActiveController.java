package com.example.rrsystem.Controllers.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Admin.Manager.ManagerActiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/managers")
public class ManagerActiveController {

    @Autowired
    ManagerActiveRepository managerActiveRepository;

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        UserInfo manager = managerActiveRepository.findById(id).orElse(null);
        if (manager != null) {
            manager.setActiveness(1);
            manager.setDeletedAt(LocalDateTime.now());
            managerActiveRepository.save(manager);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
