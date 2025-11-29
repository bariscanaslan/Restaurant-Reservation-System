package com.example.rrsystem.Repositories.Admin.UpdatePersonelInfo;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpdatePersonelInfoARepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT u FROM UserInfo u WHERE u.username = :username")
    UserInfo findByUsername(String username);
}
