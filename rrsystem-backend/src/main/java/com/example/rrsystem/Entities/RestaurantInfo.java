package com.example.rrsystem.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant-info", schema = "restaurant-reservation-system")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_owner")
    private String restaurantOwner;

    @ManyToOne
    @JoinColumn(name = "restaurant_cuisine", referencedColumnName = "cuisine_id")
    private Cuisine cuisine;

    @ManyToOne
    @JoinColumn(name = "restaurant_location", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "restaurant_customer", referencedColumnName = "user_id")
    private UserInfo customer;

    @Column(name = "restaurant_website")
    private String restaurantWebsite;

    @Column(name = "restaurant_email")
    private String restaurantEmail;

    @Column(name = "restaurant_phone")
    private String restaurantPhone;

    @Column(name = "hours_of_operation")
    private String hoursOfOperation;

    @Column(name = "dress_code")
    private String dressCode;

    @Column(name = "parking_details")
    private String parkingDetails;

    @Column(name = "restaurant_creation")
    private LocalDateTime restaurantCreation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public UserInfo getUserInfo() {
        return customer;
    }

    public void setUserInfo(UserInfo customer) {
        this.customer = customer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        this.restaurantWebsite = restaurantWebsite;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getParkingDetails() {
        return parkingDetails;
    }

    public void setParkingDetails(String parkingDetails) {
        this.parkingDetails = parkingDetails;
    }

    public LocalDateTime getRestaurantCreation() {
        return restaurantCreation;
    }

    public void setRestaurantCreation(LocalDateTime restaurantCreation) {
        this.restaurantCreation = restaurantCreation;
    }

}
