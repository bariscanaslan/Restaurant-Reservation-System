package com.example.rrsystem.Repositories.Admin.Plan;

import com.example.rrsystem.Entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditPricesRepository extends JpaRepository<Plan, Long>{
    List<Plan> findByPlanActiveness(int planActiveness);
}
