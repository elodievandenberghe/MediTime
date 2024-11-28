package be.vives.ti.MediTime.service;


import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.domain.Medications;
import be.vives.ti.MediTime.repository.CategoryRepository;
import be.vives.ti.MediTime.repository.DosingRepository;
import be.vives.ti.MediTime.repository.MedicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationsService {

    @Autowired
    private MedicationsRepository medicationsRepository;

    @Autowired
    private CategoryRepository categoriesRepository;

    @Autowired
    private DosingRepository dosingRepository;

    // Constructor
    public MedicationsService() {}

    // Get all Medications
    public Page<Medications> getAllMedications(Pageable pageable) {
        return medicationsRepository.findAll(pageable);
    }
    // Get Medications by Id
    public Optional<Medications> getMedicationsById(Integer id) {
        return medicationsRepository.findById(id);
    }

    // Create Medications
    public Medications createMedications(Medications medications) {
        if (categoriesRepository.findById(medications.getCategory().getId()).isEmpty()) {
            throw new RuntimeException("Category id doesn't exist");
        }
        if (dosingRepository.findById(medications.getDosing().getId()).isEmpty()) {
            throw new RuntimeException("Dosing id doesn't exist");
        }
        return medicationsRepository.save(medications);
    }

    public Medications updateMedications(Integer id, Medications updatedMedications) {
        return medicationsRepository.findById(id).map(medications -> {
            medications.setName(updatedMedications.getName());
            medications.setCategory(updatedMedications.getCategory());
            medications.setDosing(updatedMedications.getDosing());
            return medicationsRepository.save(medications);
        }).orElseThrow(() -> new RuntimeException("Medications with ID " + id + " not found"));
    }

    public void deleteMedications(Integer id) {
        if (medicationsRepository.existsById(id)) {
            medicationsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Medications with ID " + id + " not found");
        }
    }
}