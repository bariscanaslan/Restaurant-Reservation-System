package com.example.rrsystem.Repositories.Reservation;

import com.example.rrsystem.Entities.TableInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableInfoRepository extends JpaRepository<TableInfo, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE TableInfo t SET t.tableAvailable = 1 WHERE t.id IN :tableIds")
    void activateTables(List<Long> tableIds);

    List<TableInfo> findByRestaurantIdAndTableAvailableAndTableCapacityGreaterThanEqual(Long restaurantId, int tableAvailable, int tableCapacity);
}