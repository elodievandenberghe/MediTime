package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users , Integer> {
    Optional<Users> findByEmail(String username);
}
