package com.example.rrsystem.Entities;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation-request", schema = "restaurant-reservation-system")
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_request_id")
    private Long id;

    @Column(name = "reservation_request_date")
    private Date reservationRequestDate;

    @Column(name = "reservation_request_time")
    private Time reservationRequestTime;

    @Column(name = "reservation_request_people_no")
    private int peopleNo;

    @Column(name = "reservation_request_phone")
    private String phone;

    @Column(name = "reservation_request_note")
    private String note;

    @Column(name = "reservation_request_email")
    private String email;

    @Column(name = "reservation_request_name")
    private String name;

    @Column(name = "reservation_request_surname")
    private String surname;

    @Column(name = "request_send_date")
    private LocalDateTime requestSendDate;

    @Column(name = "request_status")
    private int requestStatus;

    @Column(name = "request_response")
    private String requestResponse;

    @ManyToOne
    @JoinColumn(name = "reservation_restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantInfo restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationRequestDate() {
        return reservationRequestDate;
    }

    public void setReservationRequestDate(Date reservationRequestDate) {
        this.reservationRequestDate = reservationRequestDate;
    }

    public Time getReservationRequestTime() {
        return reservationRequestTime;
    }

    public void setReservationRequestTime(Time reservationRequestTime) {
        this.reservationRequestTime = reservationRequestTime;
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

    public RestaurantInfo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantInfo restaurant) {
        this.restaurant = restaurant;
    }
}
