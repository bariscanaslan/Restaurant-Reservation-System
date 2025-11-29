package com.example.rrsystem.Repositories.Admin.Customer;

import com.example.rrsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerListRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.user LEFT JOIN FETCH c.restaurant LEFT JOIN FETCH c.payment ORDER BY c.user.username ASC")
    List<Customer> findAllCustomers();
}
