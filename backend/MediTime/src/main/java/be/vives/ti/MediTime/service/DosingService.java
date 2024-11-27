package be.vives.ti.MediTime.service;

import be.vives.ti.MediTime.domain.Dosing;
import be.vives.ti.MediTime.repository.DosingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DosingService {

    @Autowired
    private DosingRepository dosingRepository;

    // Constructor
    public DosingService() {}

    // Get all Dosing types
    public List<Dosing> getAllDosing() {
        return dosingRepository.findAll();
    }

    // Get Dosing by Id
    public Optional<Dosing> getDosingById(Integer id) {
        return dosingRepository.findById(id);
    }

    // Create a new Dosing
    public Dosing createDosing(Dosing dosing) {
        return dosingRepository.save(dosing);
    }

    // Update Dosing by ID
    public Dosing updateDosing(Integer id, Dosing updatedDosing) {
        return dosingRepository.findById(id).map(dosing -> {
            dosing.setDosingType(updatedDosing.getDosingType());
            return dosingRepository.save(dosing);
        }).orElseThrow(() -> new RuntimeException("Dosing with ID " + id + " not found"));
    }

    // Delete Dosing by ID
    public void deleteDosing(Integer id) {
        if (dosingRepository.existsById(id)) {
            dosingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dosing with ID " + id + " not found");
        }
    }
}
