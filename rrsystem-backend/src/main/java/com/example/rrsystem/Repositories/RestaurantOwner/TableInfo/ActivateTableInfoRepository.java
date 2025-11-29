package com.example.rrsystem.Repositories.RestaurantOwner.TableInfo;

import com.example.rrsystem.Entities.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivateTableInfoRepository extends JpaRepository<TableInfo, Long> {
}
