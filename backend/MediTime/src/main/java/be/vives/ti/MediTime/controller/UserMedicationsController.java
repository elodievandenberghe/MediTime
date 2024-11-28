package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.UserMedications;
import be.vives.ti.MediTime.exceptions.ResourceNotFoundException;
import be.vives.ti.MediTime.request.UserMedicationsRequest;
import be.vives.ti.MediTime.response.UserMedicationsResponse;
import be.vives.ti.MediTime.service.MedicationsService;
import be.vives.ti.MediTime.service.UserMedicationsService;
import be.vives.ti.MediTime.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("user-medications")
public class UserMedicationsController {

    private final UserMedicationsService userMedicationsService;
    private final MedicationsService medicationsService;
    private final UserService userService;

    @Autowired
    public UserMedicationsController(UserMedicationsService userMedicationsService, MedicationsService medicationsService, UserService userService) {
        this.userMedicationsService = userMedicationsService;
        this.medicationsService = medicationsService;
        this.userService = userService;
    }

    // Get all user medications
    // Get all medication schedules
    @GetMapping
    public Page<UserMedicationsResponse> getAllCategories(Pageable pageable) {
        return this.userMedicationsService.getAllUserMedications(pageable).map(UserMedicationsResponse::new);
    }

    // Get dosing by ID
    @GetMapping("/{id}")
    public UserMedicationsResponse getDosingById(@PathVariable("id") Integer id) {
        return new UserMedicationsResponse(userMedicationsService.getUserMedicationsById(id).orElseThrow(() -> new ResourceNotFoundException(id, "dosing")));
    }

    // Create new user medication
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUserMedications(@Valid @RequestBody UserMedicationsRequest userMedicationsRequest) {
        UserMedications newUserMedications = userMedicationsService.createUserMedications(new UserMedications(
                userService.getUserById(userMedicationsRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException(userMedicationsRequest.getUserId().toString())),
                medicationsService.getMedicationsById(userMedicationsRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException(userMedicationsRequest.getMedicationId().toString()))
        ));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUserMedications.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // Update user medication by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserMedicationsResponse> updateUserMedications(@PathVariable("id") Integer id, @Valid @RequestBody UserMedicationsRequest updatedUserMedicationsRequest) {
        UserMedications updatedUserMedications = userMedicationsService.updateUserMedications(id, new UserMedications(
                userService.getUserById(updatedUserMedicationsRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException(updatedUserMedicationsRequest.getUserId().toString())),
                medicationsService.getMedicationsById(updatedUserMedicationsRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException(updatedUserMedicationsRequest.getMedicationId().toString()))));
        return new ResponseEntity<>(new UserMedicationsResponse(updatedUserMedications), HttpStatus.OK);
    }

    // Delete user medication by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserMedications(@PathVariable("id") Integer id) {
        userMedicationsService.deleteUserMedications(id);
    }
}
