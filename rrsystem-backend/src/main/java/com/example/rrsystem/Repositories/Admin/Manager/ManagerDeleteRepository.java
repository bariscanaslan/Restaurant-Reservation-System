package com.example.rrsystem.Repositories.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDeleteRepository extends JpaRepository<UserInfo, Long> {
}
