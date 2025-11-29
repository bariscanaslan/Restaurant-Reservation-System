package com.example.rrsystem.Services.RestaurantOwner.Reservations;

import com.example.rrsystem.Entities.ReservationInfo;
import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.Reservation.ReservationInfoRepository;
import com.example.rrsystem.Repositories.Reservation.TableInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelReservationService {

    private final ReservationInfoRepository reservationInfoRepository;
    private final TableInfoRepository tableInfoRepository;

    public CancelReservationService(ReservationInfoRepository reservationInfoRepository, TableInfoRepository tableInfoRepository) {
        this.reservationInfoRepository = reservationInfoRepository;
        this.tableInfoRepository = tableInfoRepository;
    }

    @Transactional
    public boolean cancelReservation(Long reservationId) {
        try {
            ReservationInfo reservation = reservationInfoRepository.findById(reservationId)
                    .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

            if (reservation.getTableIds() != null && !reservation.getTableIds().isEmpty()) {
                String[] tableIdArray = reservation.getTableIds().split(",");

                for (String tableIdStr : tableIdArray) {
                    Long tableId = Long.parseLong(tableIdStr.trim());
                    TableInfo table = tableInfoRepository.findById(tableId).orElse(null);

                    if (table != null) {
                        table.setTableAvailable(1);
                        table.setTableDeletion(LocalDateTime.now());
                        tableInfoRepository.save(table);
                    }
                }
            }
            reservationInfoRepository.delete(reservation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
