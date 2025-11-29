package com.example.rrsystem.Entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant-request", schema = "restaurant-reservation-system")
public class RestaurantRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_request_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "r_restaurant_name")
    private String restaurantName;

    @ManyToOne
    @JoinColumn(name = "r_restaurant_cuisine", referencedColumnName = "cuisine_id")
    private Cuisine cuisine;

    @ManyToOne
    @JoinColumn(name = "r_restaurant_location", referencedColumnName = "location_id")
    private Location location;

    @Column(name = "r_restaurant_owner")
    private String restaurantOwner;

    @Column(name = "r_restaurant_website")
    private String restaurantWebsite;

    @Column(name = "r_restaurant_email")
    private String restaurantEmail;

    @Column(name = "r_restaurant_phone")
    private String restaurantPhone;

    @Column(name = "r_restaurant_hours")
    private String hoursOfOperation;

    @Column(name = "r_restaurant_dress")
    private String dressCode;

    @Column(name = "r_restaurant_parking")
    private String parkingDetails;

    @Column(name = "r_restaurant_doc")
    private String restaurantDocumentation;

    @Column(name = "request_send_date")
    private LocalDateTime requestSendDate;

    @Column(name = "request_status")
    private int requestStatus;

    @Column(name = "request_response")
    private String requestResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getRestaurantDocumentation() {
        return restaurantDocumentation;
    }

    public void setRestaurantDocumentation(String restaurantDocumentation) {
        this.restaurantDocumentation = restaurantDocumentation;
    }

    public LocalDateTime getRequestSendDate() {
        return requestSendDate;
    }

    public void setRequestSendDate(LocalDateTime requestSendDate) {
        this.requestSendDate = requestSendDate;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestResponse() {
        return requestResponse;
    }

    public void setRequestResponse(String requestResponse) {
        this.requestResponse = requestResponse;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
