package com.example.rrsystem.Services.Reservation;

import com.example.rrsystem.Entities.ReservationInfo;
import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.Reservation.ReservationInfoRepository;
import com.example.rrsystem.Repositories.Reservation.SessionInfoRepository;
import com.example.rrsystem.Repositories.Reservation.TableInfoRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AutoActivateTableService {

    private static final Logger logger = LoggerFactory.getLogger(AutoActivateTableService.class);

    @Autowired
    private ReservationInfoRepository reservationInfoRepository;

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private SessionInfoRepository sessionInfoRepository;

    @PostConstruct
    public void runOnStartup() {
        logger.info("Running table activation at startup...");
        activateTables();
        deactivateSessions();
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void activateTablesForExpiredReservations() {
        activateTables();
    }

    private void deactivateSessions() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        logger.info("Scheduled task started at {}", now);

        try {
            List<SessionInfo> sessions = sessionInfoRepository.findAll();
            for (SessionInfo session : sessions) {
                LocalDate sessionDate = session.getSessionDate().toLocalDate();
                LocalTime sessionStart = session.getSessionStart();
                LocalTime sessionEnd = session.getSessionEnd();

                LocalDateTime sessionStartDateTime = sessionDate.atTime(sessionStart);
                LocalDateTime sessionEndDateTime = sessionDate.atTime(sessionEnd);

                if(session.getSessionActiveness() == 1) {
                    if (sessionEnd.isBefore(sessionStart)) {
                        sessionEndDateTime = sessionEndDateTime.plusDays(1);
                    }

                    if (sessionDate.isBefore(now.toLocalDate()) ||
                            (sessionDate.isEqual(now.toLocalDate()) && sessionEndDateTime.isBefore(now))) {

                        session.setSessionActiveness(0);
                        session.setSessionDeletion(LocalDateTime.now());
                        logger.info("Session deactived with ID: {}", session.getId());
                        sessionInfoRepository.save(session);
                    }
                } else {
                    logger.info("Session is already deactive with ID: {}", session.getId());
                }

            }

        } catch (Exception e) {
            logger.error("Error occurred while processing expired reservations: {}", e.getMessage(), e);
        }
    }

    private void activateTables() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        logger.info("Scheduled task started at {}", now);

        try {
            List<ReservationInfo> reservations = reservationInfoRepository.findExpiredReservations(nowDate);
            logger.info("Found {} expired reservations to process", reservations.size());

            for (ReservationInfo reservation : reservations) {
                SessionInfo session = reservation.getSession();
                LocalDate sessionDate = session.getSessionDate().toLocalDate();
                LocalTime sessionStart = session.getSessionStart();
                LocalTime sessionEnd = session.getSessionEnd();

                LocalDateTime sessionStartDateTime = sessionDate.atTime(sessionStart);
                LocalDateTime sessionEndDateTime = sessionDate.atTime(sessionEnd);

                if(session.getSessionActiveness() == 1)
                {

                    if (sessionEnd.isBefore(sessionStart)) {
                        sessionEndDateTime = sessionEndDateTime.plusDays(1);
                    }

                    if (sessionDate.isBefore(now.toLocalDate()) ||
                            (sessionDate.isEqual(now.toLocalDate()) && sessionEndDateTime.isBefore(now))) {

                        String[] tableIds = reservation.getTableIds().split(",");
                        logger.info("Activating tables for reservation with ID: {}", reservation.getId());

                        for (String tableIdStr : tableIds) {
                            Long tableId = Long.parseLong(tableIdStr.trim());
                            TableInfo table = tableInfoRepository.findById(tableId).orElse(null);
                            if (table != null) {
                                if(table.getTableAvailable() != 1) {
                                    table.setTableAvailable(1);
                                    table.setTableDeletion(LocalDateTime.now());
                                    tableInfoRepository.save(table);
                                }
                                logger.info("Table with ID: {} is now available.", tableId);
                            } else {
                                logger.warn("Table with ID: {} not found.", tableId);
                            }
                        }
                    }
                } else {
                    logger.info("Session is already deactive with ID: {}", session.getId());
                }
            }

            logger.info("Scheduled task completed at {}", LocalDateTime.now());

        } catch (Exception e) {
            logger.error("Error occurred while processing expired reservations: {}", e.getMessage(), e);
        }
    }

}
