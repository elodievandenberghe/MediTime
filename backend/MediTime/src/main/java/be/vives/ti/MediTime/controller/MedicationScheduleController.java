package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.MedicationSchedule;
import be.vives.ti.MediTime.exceptions.ResourceNotFoundException;
import be.vives.ti.MediTime.request.MedicationScheduleRequest;
import be.vives.ti.MediTime.response.CategoriesResponse;
import be.vives.ti.MediTime.response.MedicationScheduleResponse;
import be.vives.ti.MediTime.response.MedicationsResponse;
import be.vives.ti.MediTime.service.MedicationScheduleService;
import be.vives.ti.MediTime.service.UserMedicationsService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/medication-schedules")
@RestController
public class MedicationScheduleController {

    private final MedicationScheduleService medicationScheduleService;
    private final UserMedicationsService userMedicationsService;

    @Autowired
    public MedicationScheduleController(MedicationScheduleService medicationScheduleService, UserMedicationsService userMedicationsService) {
        this.medicationScheduleService = medicationScheduleService;
        this.userMedicationsService = userMedicationsService;
    }

    // Get all medication schedules
    @GetMapping
    public Page<MedicationScheduleResponse> getAllCategories(Pageable pageable) {
        return this.medicationScheduleService.getAllMedicationSchedules(pageable).map(MedicationScheduleResponse::new);
    }

    // Get dosing by ID
    @GetMapping("/{id}")
    public MedicationScheduleResponse getDosingById(@PathVariable("id") Integer id) {
        return new MedicationScheduleResponse(medicationScheduleService.getMedicationScheduleById(id).orElseThrow(() -> new ResourceNotFoundException(id, "dosing")));
    }

    // Create new medication schedule
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createMedicationSchedule(@RequestBody @Valid MedicationScheduleRequest medicationScheduleRequest) {
        MedicationSchedule createdSchedule = medicationScheduleService.createMedicationSchedule(new MedicationSchedule(
                userMedicationsService.getUserMedicationsById(medicationScheduleRequest.getUserMedicationId()).orElseThrow(() -> new ResourceNotFoundException(medicationScheduleRequest.getUserMedicationId().toString())),
                medicationScheduleRequest.getTime(),
                medicationScheduleRequest.getWeekMask()
        ));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSchedule.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Update medication schedule by ID
    @PutMapping("/{id}")
    public ResponseEntity<MedicationScheduleResponse> updateMedicationSchedule(
            @PathVariable("id") Integer id,
            @RequestBody @Valid MedicationScheduleRequest updatedMedicationScheduleRequest) {

        MedicationSchedule updatedMedicationSchedule = medicationScheduleService.updateMedicationSchedule(id, new MedicationSchedule(
                userMedicationsService.getUserMedicationsById(updatedMedicationScheduleRequest.getUserMedicationId()).orElseThrow(() -> new ResourceNotFoundException(updatedMedicationScheduleRequest.getUserMedicationId().toString())),
                updatedMedicationScheduleRequest.getTime(),
                updatedMedicationScheduleRequest.getWeekMask()
        ));

        return new ResponseEntity<>(new MedicationScheduleResponse(updatedMedicationSchedule), HttpStatus.OK);
    }

    // Delete medication schedule by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicationSchedule(@PathVariable("id") Integer id) {
        medicationScheduleService.deleteMedicationSchedule(id);
    }
}
