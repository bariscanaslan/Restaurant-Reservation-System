package com.example.rrsystem.Controllers.RestaurantOwner.UpdatePersonelInfo;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.UpdatePersonelInfo.UpdatePersonelInfoRORepository;
import com.example.rrsystem.Security.JwtUtil;
import com.example.rrsystem.Services.Mail.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequestMapping("/api/restaurant-owner/user-info")
public class UpdatePersonelInfoROController {

    private final UpdatePersonelInfoRORepository updatePersonelInfoRORepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public UpdatePersonelInfoROController(UpdatePersonelInfoRORepository updatePersonelInfoRORepository, JwtUtil jwtUtil, EmailService emailService) {
        this.updatePersonelInfoRORepository = updatePersonelInfoRORepository;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    @GetMapping("/get-info")
    public ResponseEntity<?> getUserInfo(
            @CookieValue(value = "jwt", required = false) String jwtToken
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        String username = jwtUtil.extractUsername(jwtToken);
        UserInfo user = updatePersonelInfoRORepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<?> updateUserInfo(
            @CookieValue(value = "jwt", required = false) String jwtToken,
            @RequestBody UserInfo updatedUserInfo
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        Long userId = jwtUtil.extractUserId(jwtToken);
        UserInfo existingUser = updatePersonelInfoRORepository.findById(userId).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        existingUser.setUsername(updatedUserInfo.getUsername());
        existingUser.setName(updatedUserInfo.getName());
        existingUser.setSurname(updatedUserInfo.getSurname());
        existingUser.setEmail(updatedUserInfo.getEmail());
        existingUser.setPhone(updatedUserInfo.getPhone());
        existingUser.setPhoto(updatedUserInfo.getPhoto());
        existingUser.setDeletedAt(LocalDateTime.now());

        updatePersonelInfoRORepository.save(existingUser);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd.MM.yyyy");
        String formattedDateTime = now.format(formatter);

        emailService.sendSimpleEmail("bariscanaslan@outlook.com", "Profile Updated!", "Dear, " + updatedUserInfo.getName() + " " + updatedUserInfo.getSurname() + "\n" + "Your user information updated successfully!\n" + formattedDateTime);

        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("message", "User information updated successfully");
        }});
    }
}

