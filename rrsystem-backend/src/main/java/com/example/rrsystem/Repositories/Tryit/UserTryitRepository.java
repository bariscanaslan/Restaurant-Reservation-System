package com.example.rrsystem.Repositories.Tryit;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTryitRepository extends JpaRepository<UserInfo, Long> {
    boolean existsByUsername(String username);
}