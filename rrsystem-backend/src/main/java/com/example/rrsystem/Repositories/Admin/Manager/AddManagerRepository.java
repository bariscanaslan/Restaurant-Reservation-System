package com.example.rrsystem.Repositories.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddManagerRepository extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findByUsername(String username);
}
