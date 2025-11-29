package com.example.rrsystem.Services.Navigation;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Navigation.AdminUserInfoRepository;
import com.example.rrsystem.Repositories.Navigation.ManagerUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ManagerUserInfoService {

    @Autowired
    private ManagerUserInfoRepository managerUserInfoRepository;

    public Optional<Map<String, Object>> getUserById(Long userId) {
        return managerUserInfoRepository.findByUserId(userId);
    }
}