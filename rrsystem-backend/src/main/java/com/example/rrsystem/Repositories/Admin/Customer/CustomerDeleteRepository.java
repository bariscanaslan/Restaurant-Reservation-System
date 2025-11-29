package com.example.rrsystem.Repositories.Admin.Customer;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeleteRepository extends JpaRepository<UserInfo, Long> {
}
