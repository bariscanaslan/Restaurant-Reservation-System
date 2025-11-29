package com.example.rrsystem.Controllers.Navigation;

import com.example.rrsystem.Services.Navigation.AdminUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/navigation-admin")
public class NavigationAdminController {

    @Autowired
    private AdminUserInfoService adminUserInfoService;

    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable Long userId) {
        Optional<Map<String, Object>> userInfo = adminUserInfoService.getUserById(userId);
        return userInfo.orElse(null);
    }
}
