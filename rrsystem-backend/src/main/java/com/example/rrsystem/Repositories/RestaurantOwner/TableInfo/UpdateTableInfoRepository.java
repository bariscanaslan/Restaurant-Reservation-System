package com.example.rrsystem.Repositories.RestaurantOwner.TableInfo;

import com.example.rrsystem.Entities.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateTableInfoRepository extends JpaRepository<TableInfo, Long> {
    boolean existsByTableName(String tableName);
}
