package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.MedicationSchedule;
import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationScheduleRepository extends JpaRepository<MedicationSchedule, Integer> {
}
