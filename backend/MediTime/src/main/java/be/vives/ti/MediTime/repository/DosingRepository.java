package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.Dosing;
import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DosingRepository extends JpaRepository<Dosing, Integer> {
}
