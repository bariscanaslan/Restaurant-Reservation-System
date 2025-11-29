package com.example.rrsystem.Controllers.Admin.Manager;

import com.example.rrsystem.Entities.UserInfo;
import com.example.rrsystem.Repositories.Admin.Manager.ManagerListRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/managers")
public class ManagerListController {

    private final ManagerListRepository managerRepository;

    public ManagerListController(ManagerListRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping
    public List<UserInfo> getAllManagers () {
        return managerRepository.findByUserType(2);
    }
}
