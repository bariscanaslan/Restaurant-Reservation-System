package com.example.rrsystem.Services.Navigation;

import com.example.rrsystem.Repositories.Navigation.AdminUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AdminUserInfoService {

    @Autowired
    private AdminUserInfoRepository adminUserInfoRepository;

    public Optional<Map<String, Object>> getUserById(Long userId) {
        return adminUserInfoRepository.findByUserId(userId);
    }
}
