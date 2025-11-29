package com.example.rrsystem.Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "session-info", schema = "restaurant-reservation-system")
public class SessionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @JsonProperty("session_date")
    @Column(name = "session_date")
    private Date sessionDate;

    @JsonProperty("session_start")
    @Column(name = "session_start")
    private LocalTime sessionStart;

    @JsonProperty("session_end")
    @Column(name = "session_end")
    private LocalTime sessionEnd;

    @ManyToOne
    @JoinColumn(name = "s_restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantInfo restaurant;

    @JsonProperty("session_activeness")
    @Column(name = "session_activeness")
    private int sessionActiveness;

    @Column(name = "session_creation")
    private LocalDateTime sessionCreation;

    @Column(name = "session_deletion")
    private LocalDateTime sessionDeletion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(LocalTime sessionStart) {
        this.sessionStart = sessionStart;
    }

    public LocalTime getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalTime sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public int getSessionActiveness() {
        return sessionActiveness;
    }

    public void setSessionActiveness(int sessionActiveness) {
        this.sessionActiveness = sessionActiveness;
    }

    public LocalDateTime getSessionCreation() {
        return sessionCreation;
    }

    public void setSessionCreation(LocalDateTime sessionCreation) {
        this.sessionCreation = sessionCreation;
    }

    public LocalDateTime getSessionDeletion() {
        return sessionDeletion;
    }

    public void setSessionDeletion(LocalDateTime sessionDeletion) {
        this.sessionDeletion = sessionDeletion;
    }

    public RestaurantInfo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantInfo restaurant) {
        this.restaurant = restaurant;
    }


}
