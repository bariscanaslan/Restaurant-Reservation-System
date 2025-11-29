package com.example.rrsystem.Services.RestaurantOwner.CreateSessions;


import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.CreateSessions.AddSessionRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateSessionService {

    private final AddSessionRepository createSessionsRepository;

    public CreateSessionService(AddSessionRepository createSessionsRepository) {
        this.createSessionsRepository = createSessionsRepository;
    }

    public SessionInfo createSession(SessionInfo sessionInfo) {
        sessionInfo.setSessionActiveness(1);
        sessionInfo.setSessionCreation(LocalDateTime.now());
        return createSessionsRepository.save(sessionInfo);
    }

    public void activateSession(Long id) {
        SessionInfo session = createSessionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));
        session.setSessionActiveness(1);
        createSessionsRepository.save(session);
    }

    public void deactivateSession(Long id) {
        SessionInfo session = createSessionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));
        session.setSessionActiveness(0);
        createSessionsRepository.save(session);
    }

    public void deleteSession(Long id) {
        if (createSessionsRepository.existsById(id)) {
            createSessionsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Session not found");
        }
    }


}
