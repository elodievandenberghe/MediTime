package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.Medications;
import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationsRepository extends JpaRepository<Medications, Integer> {
}
