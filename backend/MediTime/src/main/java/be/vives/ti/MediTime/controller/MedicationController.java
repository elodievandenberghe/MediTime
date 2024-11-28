package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Medications;
import be.vives.ti.MediTime.service.MedicationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("medications")
@RestController
public class MedicationController {

    private final MedicationsService medicationsService;

    @Autowired
    public MedicationController(MedicationsService medicationsService) {
        this.medicationsService = medicationsService;
    }

    // Get all medications
    @GetMapping
    public List<Medications> findAllMedications() {
        return medicationsService.getAllMedications();
    }

    // Get medication by ID
    @GetMapping("/{id}")
    public Optional<Medications> findMedicationsById(@PathVariable("id") Integer id) {
        return medicationsService.getMedicationsById(id);
    }

    // Create new medication
    @PostMapping
    public Medications createMedications(@Valid @RequestBody Medications medications) {
        return medicationsService.createMedications(medications);
    }

    // Update medication by ID
    @PutMapping("/{id}")
    public Medications updateMedications(@PathVariable("id") Integer id, @Valid @RequestBody Medications updatedMedications) {
        return medicationsService.updateMedications(id, updatedMedications);
    }

    // Delete medication by ID
    @DeleteMapping("/{id}")
    public void deleteMedications(@PathVariable("id") Integer id) {
        medicationsService.deleteMedications(id);
    }
}
