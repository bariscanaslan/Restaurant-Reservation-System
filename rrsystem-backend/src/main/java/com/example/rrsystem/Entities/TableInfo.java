package com.example.rrsystem.Entities;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "table-info", schema = "restaurant-reservation-system")
public class TableInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_capacity")
    private int tableCapacity;

    @ManyToOne
    @JoinColumn(name = "t_restaurant_id", referencedColumnName = "restaurant_id")
    private RestaurantInfo restaurant;

    @Column(name = "table_activeness")
    private int tableActiveness;

    @Column(name = "table_creation")
    private LocalDateTime tableCreation;

    @Column(name = "table_deletion")
    private LocalDateTime tableDeletion;

    @Column(name = "table_available")
    private int tableAvailable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(int tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public RestaurantInfo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantInfo restaurant) {
        this.restaurant = restaurant;
    }

    public int getTableActiveness() {
        return tableActiveness;
    }

    public void setTableActiveness(int tableActiveness) {
        this.tableActiveness = tableActiveness;
    }

    public LocalDateTime getTableCreation() {
        return tableCreation;
    }

    public void setTableCreation(LocalDateTime tableCreation) {
        this.tableCreation = tableCreation;
    }

    public LocalDateTime getTableDeletion() {
        return tableDeletion;
    }

    public void setTableDeletion(LocalDateTime tableDeletion) {
        this.tableDeletion = tableDeletion;
    }

    public int getTableAvailable() {
        return tableAvailable;
    }

    public void setTableAvailable(int tableAvailable) {
        this.tableAvailable = tableAvailable;
    }

}
