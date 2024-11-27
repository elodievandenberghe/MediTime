package be.vives.ti.MediTime.repository;

import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
