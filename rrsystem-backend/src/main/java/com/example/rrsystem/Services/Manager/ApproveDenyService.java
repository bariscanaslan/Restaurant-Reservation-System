package com.example.rrsystem.Services.Manager;

import com.example.rrsystem.Entities.*;
import com.example.rrsystem.Repositories.Manager.RestaurantRequest.*;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ApproveDenyService {

    private final ApproveDenyRepository approveDenyRepository;
    private final RestaurantInfoCreateRepository restaurantInfoRepository;
    private final UserInfoCreateRepository userInfoRepository;
    private final PaymentCreateRepository paymentCreateRepository;
    private final CustomerCreateRepository customerCreateRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApproveDenyService(ApproveDenyRepository approveDenyRepository, RestaurantInfoCreateRepository restaurantInfoRepository, UserInfoCreateRepository userInfoRepository, PaymentCreateRepository paymentCreateRepository, CustomerCreateRepository customerCreateRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.approveDenyRepository = approveDenyRepository;
        this.restaurantInfoRepository = restaurantInfoRepository;
        this.userInfoRepository = userInfoRepository;
        this.paymentCreateRepository = paymentCreateRepository;
        this.customerCreateRepository = customerCreateRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public RestaurantRequest getRequestById(Long id) {
        return approveDenyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Request not found"));
    }

    @Transactional
    public void approveRequest(Long id, RestaurantRequest updatedRequest, String username) {
        RestaurantRequest request = getRequestById(id);
        request.setRequestStatus(1);
        approveDenyRepository.save(request);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setName(updatedRequest.getName());
        userInfo.setSurname(updatedRequest.getSurname());
        userInfo.setActiveness(1);
        userInfo.setUserType(4);
        userInfo.setCreatedAt(LocalDateTime.now());
        userInfo.setPassword(bCryptPasswordEncoder.encode("rezal12345"));
        userInfoRepository.save(userInfo);

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setUserInfo(userInfo);
        restaurantInfo.setRestaurantName(updatedRequest.getRestaurantName());
        restaurantInfo.setRestaurantOwner(updatedRequest.getRestaurantOwner());
        restaurantInfo.setCuisine(updatedRequest.getCuisine());
        restaurantInfo.setLocation(updatedRequest.getLocation());
        restaurantInfo.setRestaurantWebsite(updatedRequest.getRestaurantWebsite());
        restaurantInfo.setRestaurantEmail(updatedRequest.getRestaurantEmail());
        restaurantInfo.setRestaurantPhone(updatedRequest.getRestaurantPhone());
        restaurantInfo.setHoursOfOperation(updatedRequest.getHoursOfOperation());
        restaurantInfo.setDressCode(updatedRequest.getDressCode());
        restaurantInfo.setParkingDetails(updatedRequest.getParkingDetails());
        restaurantInfo.setRestaurantCreation(LocalDateTime.now());
        restaurantInfoRepository.save(restaurantInfo);

        Plan plan = paymentCreateRepository.findById(3L)
                .map(Payment::getPlan)
                .orElseThrow(() -> new NoSuchElementException("Plan not found"));

        Payment payment = new Payment();
        payment.setPlan(plan);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setExpirationDate(LocalDateTime.now().plusWeeks(1));
        payment.setActiveQuota(50000);
        paymentCreateRepository.save(payment);

        Customer customer = new Customer();
        customer.setUser(userInfo);
        customer.setRestaurant(restaurantInfo);
        customer.setPayment(payment);
        customerCreateRepository.save(customer);

    }

    @Transactional
    public void denyRequest(Long id, String reason) {
        RestaurantRequest request = getRequestById(id);
        request.setRequestStatus(2);
        request.setRequestResponse(reason);
        approveDenyRepository.save(request);
    }
}