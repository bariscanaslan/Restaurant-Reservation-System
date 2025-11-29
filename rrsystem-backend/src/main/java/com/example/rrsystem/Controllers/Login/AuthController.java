package com.example.rrsystem.Controllers.Login;

import com.example.rrsystem.Entities.Customer;
import com.example.rrsystem.Entities.LoginRequest;
import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Security.CustomerRepository;
import com.example.rrsystem.Security.JwtUtil;
import com.example.rrsystem.Security.UserPrincipal;
import com.example.rrsystem.Security.UserRepository;
import com.example.rrsystem.Services.Login.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil, CustomerRepository customerRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            if (!isAuthenticated) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid username or password!"));
            }

            UserInfo user = userRepository.findByUsername(loginRequest.getUsername());
            int userType = user.getUserType();
            String token = null;

            if (user.getActiveness() == 0) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Account is inactive."));
            }

            if(userType == 3 || userType == 4) {
                Customer customer = customerRepository.findByUserId(user.getId());
                Long customerId = customer.getId();
                int customerIdInt = customerId.intValue();
                token = jwtUtil.generateToken(user.getUsername(), user.getUserType(), customerIdInt, user.getId());
            } else {
                token = jwtUtil.generateToken(user.getUsername(), user.getUserType(), 0, user.getId());
            }

            ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(24 * 60 * 60)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            UserPrincipal userPrincipal = new UserPrincipal(user);
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "user", userPrincipal,
                    "token", token
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred!"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(@CookieValue(value = "jwt", required = false) String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Unauthorized"));
        }

        String username = jwtUtil.extractUsername(token);
        UserInfo user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not found"));
        }

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "userType", user.getUserType()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}