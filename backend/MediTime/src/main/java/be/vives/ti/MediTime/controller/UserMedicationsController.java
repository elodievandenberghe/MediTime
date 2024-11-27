package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.UserMedications;
import be.vives.ti.MediTime.service.UserMedicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("user-medications")
@RestController
public class UserMedicationsController {

    private final UserMedicationsService userMedicationsService;

    @Autowired
    public UserMedicationsController(UserMedicationsService userMedicationsService) {
        this.userMedicationsService = userMedicationsService;
    }

    // Get all user medications
    @GetMapping
    public List<UserMedications> findAllUserMedications() {
        return userMedicationsService.getAllUserMedications();
    }

    // Get user medication by ID
    @GetMapping("/{id}")
    public Optional<UserMedications> findUserMedicationsById(@PathVariable("id") Integer id) {
        return userMedicationsService.getUserMedicationsById(id);
    }

    // Create new user medication
    @PostMapping
    public UserMedications createUserMedications(@RequestBody UserMedications userMedications) {
        return userMedicationsService.createUserMedications(userMedications);
    }

    // Update user medication by ID
    @PutMapping("/{id}")
    public UserMedications updateUserMedications(@PathVariable("id") Integer id, @RequestBody UserMedications updatedUserMedications) {
        return userMedicationsService.updateUserMedications(id, updatedUserMedications);
    }

    // Delete user medication by ID
    @DeleteMapping("/{id}")
    public void deleteUserMedications(@PathVariable("id") Integer id) {
        userMedicationsService.deleteUserMedications(id);
    }
}
