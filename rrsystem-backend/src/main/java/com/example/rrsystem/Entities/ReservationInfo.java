package com.example.rrsystem.Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation-info", schema = "restaurant-reservation-system")
public class ReservationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "r_session_id", referencedColumnName = "session_id")
    private SessionInfo session;

    @ManyToOne
    @JoinColumn(name = "r_restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantInfo restaurant;

    @Column(name = "r_table_ids")
    private String tableIds;

    @Column(name = "reservation_people_no")
    private int peopleNo;

    @Column(name = "reservation_phone")
    private String phone;

    @Column(name = "reservation_note")
    private String note;

    @Column(name = "reservation_email")
    private String email;

    @Column(name = "reservation_name")
    private String name;

    @Column(name = "reservation_surname")
    private String surname;

    @Column(name = "reservation_send_date")
    private LocalDateTime sendDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionInfo getSession() {
        return session;
    }

    public void setSession(SessionInfo session) {
        this.session = session;
    }

    public RestaurantInfo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantInfo restaurant) {
        this.restaurant = restaurant;
    }

    public String getTableIds() {
        return tableIds;
    }

    public void setTableIds(String tableIds) {
        this.tableIds = tableIds;
    }

    public int getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(int peopleNo) {
        this.peopleNo = peopleNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }



}
