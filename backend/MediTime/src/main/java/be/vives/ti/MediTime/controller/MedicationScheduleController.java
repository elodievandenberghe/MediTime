package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.MedicationSchedule;
import be.vives.ti.MediTime.service.MedicationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("medication-schedules")
@RestController
public class MedicationScheduleController {

    private final MedicationScheduleService medicationScheduleService;

    @Autowired
    public MedicationScheduleController(MedicationScheduleService medicationScheduleService) {
        this.medicationScheduleService = medicationScheduleService;
    }

    // Get all medication schedules
    @GetMapping
    public List<MedicationSchedule> findAllMedicationSchedules() {
        return medicationScheduleService.getAllMedicationSchedules();
    }

    // Get medication schedule by ID
    @GetMapping("/{id}")
    public Optional<MedicationSchedule> findMedicationScheduleById(@PathVariable("id") Integer id) {
        return medicationScheduleService.getMedicationScheduleById(id);
    }

    // Create new medication schedule
    @PostMapping
    public MedicationSchedule createMedicationSchedule(@RequestBody MedicationSchedule medicationSchedule) {
        return medicationScheduleService.createMedicationSchedule(medicationSchedule);
    }

    // Update medication schedule by ID
    @PutMapping("/{id}")
    public MedicationSchedule updateMedicationSchedule(@PathVariable("id") Integer id,
                                                       @RequestBody MedicationSchedule updatedMedicationSchedule) {
        return medicationScheduleService.updateMedicationSchedule(id, updatedMedicationSchedule);
    }

    // Delete medication schedule by ID
    @DeleteMapping("/{id}")
    public void deleteMedicationSchedule(@PathVariable("id") Integer id) {
        medicationScheduleService.deleteMedicationSchedule(id);
    }
}

