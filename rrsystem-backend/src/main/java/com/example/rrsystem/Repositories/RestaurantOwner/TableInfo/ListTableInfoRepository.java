package com.example.rrsystem.Repositories.RestaurantOwner.TableInfo;

import com.example.rrsystem.Entities.SessionInfo;
import com.example.rrsystem.Entities.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListTableInfoRepository extends JpaRepository<TableInfo, Long> {
    @Query("SELECT t FROM TableInfo t WHERE t.restaurant.id = :restaurantId  ORDER BY t.tableName ASC")
    List<TableInfo> findByRestaurantId(Long restaurantId);
}
