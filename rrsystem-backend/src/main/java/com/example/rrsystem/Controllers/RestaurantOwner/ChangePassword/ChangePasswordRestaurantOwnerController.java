package com.example.rrsystem.Controllers.RestaurantOwner.ChangePassword;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Admin.UpdatePersonelInfo.UpdatePersonelInfoARepository;
import com.example.rrsystem.Security.JwtUtil;
import com.example.rrsystem.Services.Mail.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant-owner/change-password")
public class ChangePasswordRestaurantOwnerController {

    private final UpdatePersonelInfoARepository updatePersonelInfoARepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ChangePasswordRestaurantOwnerController(UpdatePersonelInfoARepository updatePersonelInfoARepository, JwtUtil jwtUtil, EmailService emailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.updatePersonelInfoARepository = updatePersonelInfoARepository;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PutMapping("/change")
    public ResponseEntity<?> changePassword(
            @CookieValue(value = "jwt", required = false) String jwtToken,
            @RequestBody Map<String, String> passwordMap
    ) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token not found in cookies");
        }

        Long userId = jwtUtil.extractUserId(jwtToken);
        UserInfo existingUser = updatePersonelInfoARepository.findById(userId).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        String oldPassword = passwordMap.get("oldpassword");
        String newPassword = passwordMap.get("newpassword");
        String newPasswordAgain = passwordMap.get("newpasswordagain");

        if (!bCryptPasswordEncoder.matches(oldPassword, existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect.");
        }

        if(oldPassword.equals(newPassword)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("New password cannot be the same.");
        }

        if(newPasswordAgain.length() < 8) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password cannot be fewer than 8 characters.");
        }

        if (!newPassword.equals(newPasswordAgain)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New passwords do not match.");
        }

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,:;#^()\\[\\]{}<>|/~`_+=\\-]).{8,}$";
        if (!newPassword.matches(passwordRegex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password must contain at least 1 lowercase letter, 1 uppercase letter, 1 digit, and 1 special character.");
        }

        existingUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        updatePersonelInfoARepository.save(existingUser);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd.MM.yyyy");
        String formattedDateTime = now.format(formatter);

        emailService.sendSimpleEmail("bariscanaslan@outlook.com","Password Changed Successfully!",
                "Dear " + existingUser.getName() + " " + existingUser.getSurname() + ", " + "\n\n" +
                        "Your password has been successfully changed!\n" +
                        "If you are not aware of this change, please contact us as soon as possible.\n" +
                        "Contact Email: rezalsecurity@bariscanaslan.com" + "\n" +
                        formattedDateTime + "\n\n" +
                        "provided by rezal ©");

        emailService.sendSimpleEmail("nilaybyg2@gmail.com","Password Changed Successfully!",
                "Dear " + existingUser.getName() + " " + existingUser.getSurname() + ", " + "\n\n" +
                        "Your password has been successfully changed!\n" +
                        "If you are not aware of this change, please contact us as soon as possible.\n" +
                        "Contact Email: rezalsecurity@bariscanaslan.com" + "\n" +
                        formattedDateTime + "\n\n" +
                        "provided by rezal ©");

        emailService.sendSimpleEmail("yigitavar@hotmail.com","Password Changed Successfully!",
                "Dear " + existingUser.getName() + " " + existingUser.getSurname() + ", " + "\n\n" +
                        "Your password has been successfully changed!\n" +
                        "If you are not aware of this change, please contact us as soon as possible.\n" +
                        "Contact Email: rezalsecurity@bariscanaslan.com" + "\n" +
                        formattedDateTime + "\n\n" +
                        "provided by rezal ©");

        emailService.sendSimpleEmail("toprakkamburoglu@gmail.com","Password Changed Successfully!",
                "Dear " + existingUser.getName() + " " + existingUser.getSurname() + ", " + "\n\n" +
                        "Your password has been successfully changed!\n" +
                        "If you are not aware of this change, please contact us as soon as possible.\n" +
                        "Contact Email: rezalsecurity@bariscanaslan.com" + "\n" +
                        formattedDateTime + "\n\n" +
                        "provided by rezal ©");

        return ResponseEntity.ok().body(Map.of("message", "Password changed successfully."));
    }


}
