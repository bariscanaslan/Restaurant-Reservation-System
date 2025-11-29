package com.example.rrsystem.Repositories.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagerListRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT m FROM UserInfo m WHERE m.userType = 2 ORDER BY m.username ASC")
    List<UserInfo> findByUserType(int userType);
}
