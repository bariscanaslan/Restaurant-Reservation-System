package com.example.rrsystem.Repositories.Reservation;

import com.example.rrsystem.Entities.ReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {

    @Query("SELECT r FROM ReservationInfo r " +
            "JOIN r.session s " +
            "WHERE s.sessionDate <= :nowDate")
    List<ReservationInfo> findExpiredReservations(@Param("nowDate") LocalDate nowDate);

}
