package be.vives.ti.MediTime.service;

import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.domain.MedicationSchedule;
import be.vives.ti.MediTime.repository.MedicationScheduleRepository;
import be.vives.ti.MediTime.repository.UserMedicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationScheduleService {

    @Autowired
    private MedicationScheduleRepository medicationScheduleRepository;

    @Autowired
    private UserMedicationsRepository userMedicationsRepository;

    public MedicationScheduleService() {}

    public Page<MedicationSchedule> getAllMedicationSchedules(Pageable pageable) {
        return medicationScheduleRepository.findAll(pageable);
    }
    public Optional<MedicationSchedule> getMedicationScheduleById(Integer id) {
        return medicationScheduleRepository.findById(id);
    }

    public MedicationSchedule createMedicationSchedule(MedicationSchedule medicationSchedule) {
        return medicationScheduleRepository.save(medicationSchedule);
    }
    public MedicationSchedule updateMedicationSchedule(Integer id, MedicationSchedule updatedMedicationSchedule) {
        return medicationScheduleRepository.findById(id).map(medicationSchedule -> {
            medicationSchedule.setUserMedications(updatedMedicationSchedule.getUserMedications());
            medicationSchedule.setTime(updatedMedicationSchedule.getTime());
            medicationSchedule.setWeekMask(updatedMedicationSchedule.getWeekMask());
            return medicationScheduleRepository.save(medicationSchedule);
        }).orElseThrow(() -> new RuntimeException("MedicationSchedule with ID " + id + " not found"));
    }
    public void deleteMedicationSchedule(Integer id) {
            medicationScheduleRepository.deleteById(id);

    }
}
