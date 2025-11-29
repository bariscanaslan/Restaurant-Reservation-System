package com.example.rrsystem.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment", schema = "restaurant-reservation-system")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paid_plan", referencedColumnName = "plan_id")
    @JsonManagedReference
    private Plan plan;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "active_quota")
    private int activeQuota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getActiveQuota() {
        return activeQuota;
    }

    public void setActiveQuota(int activeQuota) {
        this.activeQuota = activeQuota;
    }
}
