package com.example.rrsystem.Services.Reservation;
import com.example.rrsystem.Entities.*;
import com.example.rrsystem.Repositories.Reservation.ReservationInfoRepository;
import com.example.rrsystem.Repositories.Reservation.SessionInfoRepository;
import com.example.rrsystem.Repositories.Reservation.TableInfoRepository;
import com.example.rrsystem.Services.Mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationInfoRepository reservationRepository;

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private SessionInfoRepository sessionRepository;


    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> createReservation(ReservationInfo reservationInfo) {
        try {
            Long sessionId = reservationInfo.getSession().getId();
            int peopleNo = reservationInfo.getPeopleNo();
            Long restaurantId = reservationInfo.getRestaurant().getId();

            SessionInfo session = sessionRepository.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Session not found"));

            List<TableInfo> availableTables = tableInfoRepository.findByRestaurantIdAndTableAvailableAndTableCapacityGreaterThanEqual(restaurantId,1, 1);

            List<Long> selectedTableIds = new ArrayList<>();

            int totalCapacity = availableTables.stream()
                    .filter(t -> t.getTableAvailable() == 1)
                    .mapToInt(TableInfo::getTableCapacity)
                    .sum();

            if (peopleNo > totalCapacity) {
                throw new IllegalArgumentException("No tables of sufficient capacity were found. Please try again with fewer people.");
            }

            int remainingGuests = peopleNo;

            for (TableInfo table : availableTables) {
                if (table.getTableCapacity() <= remainingGuests) {
                    selectedTableIds.add(table.getId());
                    table.setTableAvailable(0);
                    remainingGuests -= table.getTableCapacity();
                    tableInfoRepository.save(table);
                }

                if (remainingGuests <= 0) break;
            }

            if (remainingGuests > 0) {
                for (TableInfo table : availableTables) {
                    if (!selectedTableIds.contains(table.getId()) && table.getTableAvailable() == 1) {
                        selectedTableIds.add(table.getId());
                        table.setTableAvailable(0);
                        tableInfoRepository.save(table);
                        break;
                    }
                }
            }

            reservationInfo.setSession(session);
            reservationInfo.setRestaurant(session.getRestaurant());
            reservationInfo.setTableIds(String.join(",", selectedTableIds.stream().map(String::valueOf).toArray(String[]::new)));
            reservationInfo.setSendDate(LocalDateTime.now());

            reservationRepository.save(reservationInfo);

            String note = reservationInfo.getNote();

            SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

            String formattedDate = formatterDate.format(reservationInfo.getSession().getSessionDate());
            String formattedTime = (reservationInfo.getSession().getSessionStart()).format(formatterTime);

            emailService.sendSimpleEmail("bariscanaslan@outlook.com", "Your Reservation Successfully Created!",
            "Dear " + reservationInfo.getName() + " " + reservationInfo.getSurname() + "," + "\n\n" +
                    "Your " + reservationInfo.getRestaurant().getRestaurantName() + " reservation successfully created. Reservation information:\n" +
                    "Date: " + formattedDate + "\n" +
                    "Time: " + formattedTime + "\n" +
                    "Number of Guests: " + reservationInfo.getPeopleNo() + "\n" +
                    "Special Request / Note: " + note + "\n\n" +
                    "Thanks for your choice. Please contact the restaurant in case of any problems. Restaurant Phone Number: "
                    + reservationInfo.getRestaurant().getRestaurantPhone() + "\n\n" + "provided by rezal ©");

            UserInfo user = reservationInfo.getRestaurant().getUserInfo();

            emailService.sendSimpleEmail("bariscanaslan@outlook.com", "New Reservation!",
                    "Dear " + user.getName() + " " + user.getSurname() + "," + "\n\n" +
                            "You have a new reservation for your restaurant! " + " Reservation information:\n" +
                            "Date: " + formattedDate + "\n" +
                            "Time: " + formattedTime + "\n" +
                            "Number of Guests: " + reservationInfo.getPeopleNo() + "\n" +
                            "Special Request / Note: " + note + "\n\n" +
                            "Please contact the customer if you need. Customer Phone Number: "
                            + reservationInfo.getPhone() + "\n\n" + "provided by rezal ©");



            return ResponseEntity.ok("Reservation successfully created.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
