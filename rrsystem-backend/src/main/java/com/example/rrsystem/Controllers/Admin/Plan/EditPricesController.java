package com.example.rrsystem.Controllers.Admin.Plan;

import com.example.rrsystem.Entities.Plan;
import com.example.rrsystem.Repositories.Admin.Plan.EditPricesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/prices")
public class EditPricesController {

    private final EditPricesRepository editPricesRepository;

    public EditPricesController(EditPricesRepository editPricesRepository) {
        this.editPricesRepository = editPricesRepository;
    }

    @GetMapping
    public List<Plan> getPrices(){
        return editPricesRepository.findByPlanActiveness(1);
    }
}
