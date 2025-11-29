package com.example.rrsystem.Entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "location", schema = "restaurant-reservation-system")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "location_activeness")
    private int locationActiveness;

    @Column(name = "location_creation")
    private LocalDateTime locationCreation;

    @Column(name = "location_deletion")
    private LocalDateTime locationDeletion;

    public Location(Long id, String cityName, String countryName) {
        this.id = id;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getLocationActiveness() {
        return locationActiveness;
    }

    public void setLocationActiveness(int locationActiveness) {
        this.locationActiveness = locationActiveness;
    }

    public LocalDateTime getLocationCreation() {
        return locationCreation;
    }

    public void setLocationCreation(LocalDateTime locationCreation) {
        this.locationCreation = locationCreation;
    }

    public LocalDateTime getLocationDeletion() {
        return locationDeletion;
    }

    public void setLocationDeletion(LocalDateTime locationDeletion) {
        this.locationDeletion = locationDeletion;
    }

}
