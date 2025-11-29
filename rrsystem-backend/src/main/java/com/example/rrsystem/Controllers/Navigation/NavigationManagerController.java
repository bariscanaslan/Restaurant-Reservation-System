package com.example.rrsystem.Controllers.Navigation;

import com.example.rrsystem.Services.Navigation.ManagerUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/navigation-manager")
public class NavigationManagerController {

    @Autowired
    private ManagerUserInfoService managerUserInfoService;

    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable Long userId) {
        Optional<Map<String, Object>> userInfo = managerUserInfoService.getUserById(userId);
        return userInfo.orElse(null);
    }
}