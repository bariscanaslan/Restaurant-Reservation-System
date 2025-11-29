package com.example.rrsystem.Controllers.RestaurantOwner.TableInfo;

import com.example.rrsystem.Entities.TableInfo;
import com.example.rrsystem.Repositories.RestaurantOwner.TableInfo.UpdateTableInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/restaurant-owner/tables")
public class UpdateTableInfoController {

    @Autowired
    UpdateTableInfoRepository updateTableInfoRepository;

    @PutMapping("/update-table/{id}")
    public ResponseEntity<?> deactivateSession(@PathVariable Long id, @RequestBody TableInfo updatedTableInfo) {

        TableInfo tableInfo = updateTableInfoRepository.findById(id).orElse(null);

        if (tableInfo != null) {

            boolean isTableExist = updateTableInfoRepository.existsByTableName(
                    updatedTableInfo.getTableName());

            if (isTableExist) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Table name is already exist!"));
            }

            tableInfo.setTableName(updatedTableInfo.getTableName());
            tableInfo.setTableCapacity(updatedTableInfo.getTableCapacity());
            updateTableInfoRepository.save(tableInfo);

            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(404).body("{\"success\": false, \"message\": \"Customer not found\"}");
        }
    }
}
