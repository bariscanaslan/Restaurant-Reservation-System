package com.example.rrsystem.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "plan", schema = "restaurant-reservation-system")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(name = "plan_name")
    private String planName;

    @OneToMany(mappedBy = "plan")
    @JsonBackReference
    private List<Payment> payments;

    @Column(name = "plan_price")
    private int planPrice;

    @Column(name = "plan_activeness")
    private int planActiveness;

    @Column(name = "plan_quota")
    private int planQuota;

    @Column(name = "plan_creation")
    private LocalDateTime planCreation;

    @Column(name = "plan_deletion")
    private LocalDateTime planDeletion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(int planPrice) {
        this.planPrice = planPrice;
    }

    public int getPlanActiveness() {
        return planActiveness;
    }

    public void setPlanActiveness(int planActiveness) {
        this.planActiveness = planActiveness;
    }

    public int getPlanQuota() {
        return planQuota;
    }

    public void setPlanQuota(int planQuota) {
        this.planQuota = planQuota;
    }

    public LocalDateTime getPlanCreation() {
        return planCreation;
    }

    public void setPlanCreation(LocalDateTime planCreation) {
        this.planCreation = planCreation;
    }

    public LocalDateTime getPlanDeletion() {
        return planDeletion;
    }

    public void setPlanDeletion(LocalDateTime planDeletion) {
        this.planDeletion = planDeletion;
    }


}
