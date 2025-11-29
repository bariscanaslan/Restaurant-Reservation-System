package com.example.rrsystem.Services.Admin;

import com.example.rrsystem.Entities.Plan;
import com.example.rrsystem.Repositories.Admin.Plan.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public boolean updatePlanPrice(Long id, int newPrice) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            plan.setPlanPrice(newPrice);
            planRepository.save(plan);
            return true;
        }
        return false;
    }

    public boolean updatePlanQuota(Long id, int newQuota) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            plan.setPlanQuota(newQuota);
            planRepository.save(plan);
            return true;
        }
        return false;
    }
}
