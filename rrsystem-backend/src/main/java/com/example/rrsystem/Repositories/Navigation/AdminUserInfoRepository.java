package com.example.rrsystem.Repositories.Navigation;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;

public interface AdminUserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT new map(u.id as id, u.username as username, u.name as name, u.surname as surname, u.userType as userType, u.email as email, u.photo as photo, u.phone as phone) FROM UserInfo u WHERE u.id = :userId")
    Optional<Map<String, Object>> findByUserId(@Param("userId") Long userId);
}
