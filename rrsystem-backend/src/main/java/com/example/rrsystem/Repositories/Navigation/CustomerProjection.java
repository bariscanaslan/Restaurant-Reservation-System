package com.example.rrsystem.Repositories.Navigation;

public interface CustomerProjection {
    Long getId();
    Long getUserId();
    String getUsername();
    String getName();
    String getSurname();
    String getEmail();
    String getPhone();
    String getPhoto();
    int getUserType();
    Long getRestaurantId();
}
