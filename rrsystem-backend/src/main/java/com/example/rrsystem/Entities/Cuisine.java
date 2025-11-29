package com.example.rrsystem.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuisine", schema = "restaurant-reservation-system")
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_id")
    private Long id;

    @Column(name = "cuisine_name")
    private String cuisineName;

    @Column(name = "cuisine_activeness")
    private int cuisineActiveness;

    @Column(name = "cuisine_creation")
    private LocalDateTime cuisineCreation;

    @Column(name = "cuisine_deletion")
    private LocalDateTime cuisineDeletion;

    public Cuisine(Long id, String cuisineName) {
        this.id = id;
        this.cuisineName = cuisineName;
    }

    public Cuisine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public int getCuisineActiveness() {
        return cuisineActiveness;
    }

    public void setCuisineActiveness(int cuisineActiveness) {
        this.cuisineActiveness = cuisineActiveness;
    }

    public LocalDateTime getCuisineCreation() {
        return cuisineCreation;
    }

    public void setCuisineCreation(LocalDateTime cuisineCreation) {
        this.cuisineCreation = cuisineCreation;
    }

    public LocalDateTime getCuisineDeletion() {
        return cuisineDeletion;
    }

    public void setCuisineDeletion(LocalDateTime cuisineDeletion) {
        this.cuisineDeletion = cuisineDeletion;
    }
}
