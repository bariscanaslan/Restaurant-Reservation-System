package com.example.rrsystem.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user-info", schema = "restaurant-reservation-system")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "user_type")
    private int userType;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_photo")
    private String photo;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "user_activeness")
    private int activeness;

    @Column(name = "user_creation")
    private LocalDateTime createdAt;

    @Column(name = "user_deletion")
    private LocalDateTime deletedAt;

    // Getter & Setter Methods
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public int getUserType() { return userType; }
    public void setUserType(int userType) { this.userType = userType; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getActiveness() { return activeness; }
    public void setActiveness(int activeness) { this.activeness = activeness; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
}
