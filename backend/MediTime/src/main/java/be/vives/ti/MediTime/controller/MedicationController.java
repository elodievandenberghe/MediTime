package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Medications;
import be.vives.ti.MediTime.exceptions.ResourceNotFoundException;
import be.vives.ti.MediTime.request.MedicationsRequest;
import be.vives.ti.MediTime.response.MedicationsResponse;
import be.vives.ti.MediTime.service.CategoriesService;
import be.vives.ti.MediTime.service.DosingService;
import be.vives.ti.MediTime.service.MedicationsService;
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

@RequestMapping("/api/medications")
@RestController
public class MedicationController {

    private final MedicationsService medicationsService;
    private final DosingService dosingService;
    private final CategoriesService categoriesService;

    @Autowired
    public MedicationController(MedicationsService medicationsService, DosingService dosingService, CategoriesService categoriesService) {
        this.medicationsService = medicationsService;
        this.dosingService = dosingService;
        this.categoriesService = categoriesService;
    }

    // Get all medications
    @GetMapping
    public Page<MedicationsResponse>getAllMedications(Pageable pageable) {
        return this.medicationsService.getAllMedications(pageable).map(MedicationsResponse::new);
    }

    // Get medication by ID
    @GetMapping("/{id}")
    public MedicationsResponse getMedicationById(@PathVariable("id") Integer id) {
        return new MedicationsResponse(medicationsService.getMedicationsById(id).orElseThrow(() -> new ResourceNotFoundException(id, "dosing")));
    }

    // Create new medication
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createMedication(@RequestBody @Valid MedicationsRequest medicationRequest) {
        Medications createdMedication = medicationsService.createMedications(new Medications(
                medicationRequest.getName(),
                categoriesService.getCategoryById(medicationRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(medicationRequest.getCategoryId().toString())),
                dosingService.getDosingById(medicationRequest.getDosingId()).orElseThrow(() -> new ResourceNotFoundException(medicationRequest.getDosingId().toString()))
        ));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdMedication.getId())
                .toUri();

       return ResponseEntity.created(location).build();
    }

    // Update medication by ID
    @PutMapping("/{id}")
    public ResponseEntity<MedicationsResponse> updateMedication(
            @PathVariable("id") Integer id,
            @RequestBody @Valid MedicationsRequest updatedMedicationRequest) {

        Medications updatedMedication = medicationsService.updateMedications(
                id,
                new Medications(
                        updatedMedicationRequest.getName(),
                        categoriesService.getCategoryById(updatedMedicationRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(updatedMedicationRequest.getCategoryId().toString())),
                        dosingService.getDosingById(updatedMedicationRequest.getDosingId()).orElseThrow(() -> new ResourceNotFoundException(updatedMedicationRequest.getDosingId().toString()))
                ));
        return new ResponseEntity<>(new MedicationsResponse(updatedMedication), HttpStatus.OK);
    }

    // Delete medication by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedication(@PathVariable("id") Integer id) {
        medicationsService.deleteMedications(id);
    }
}
