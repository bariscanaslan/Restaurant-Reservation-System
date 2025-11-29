package com.example.rrsystem.Services.Login;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Security.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }
    public List<UserInfo> getAllUsers() {
        return userRepository.findAll();
    }

    public UserInfo getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserInfo createUser(UserInfo user) {
        user.setPassword(bCryptPasswordEncoder.encode("rezal12345"));
        user.setCreatedAt(LocalDateTime.now());
        user.setActiveness(1);
        return userRepository.save(user);
    }

    public UserInfo updateUser(UserInfo user) {
        return userRepository.save(user);
    }

    public UserInfo deleteUser(UserInfo user) {
        user.setActiveness(0);
        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        UserInfo user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return false;
        }

        return true;
    }

}
