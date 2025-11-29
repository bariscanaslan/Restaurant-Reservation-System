package com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions;

import com.example.rrsystem.Entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeactivateSessionRepository extends JpaRepository<SessionInfo, Long> {
}
