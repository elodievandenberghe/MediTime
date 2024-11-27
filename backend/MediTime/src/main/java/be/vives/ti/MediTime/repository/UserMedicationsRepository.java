package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.UserMedications;
import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicationsRepository extends JpaRepository<UserMedications, Integer> {
}
