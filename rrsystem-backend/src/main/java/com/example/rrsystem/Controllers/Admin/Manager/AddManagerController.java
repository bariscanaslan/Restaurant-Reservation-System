package com.example.rrsystem.Controllers.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Admin.Manager.AddManagerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/managers")
public class AddManagerController {

    private final AddManagerRepository managerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AddManagerController(AddManagerRepository managerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.managerRepository = managerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/add-manager")
    public ResponseEntity<?> addManager(@RequestBody UserInfo userInfo) {

        if (managerRepository.findByUsername(userInfo.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        userInfo.setUserType(2);
        userInfo.setCreatedAt(LocalDateTime.now());
        userInfo.setActiveness(1);
        userInfo.setPassword(bCryptPasswordEncoder.encode("rezal12345"));
        UserInfo savedUser = managerRepository.save(userInfo);
        return ResponseEntity.ok(savedUser);
    }
}
