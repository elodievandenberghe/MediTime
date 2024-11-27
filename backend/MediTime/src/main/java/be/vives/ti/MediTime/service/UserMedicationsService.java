package be.vives.ti.MediTime.service;

import be.vives.ti.MediTime.domain.UserMedications;
import be.vives.ti.MediTime.repository.MedicationsRepository;
import be.vives.ti.MediTime.repository.UserMedicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMedicationsService {

    @Autowired
    private UserMedicationsRepository userMedicationsRepository;

    @Autowired
    private MedicationsRepository medicationsRepository;

    public UserMedicationsService() {}

    public List<UserMedications> getAllUserMedications() {
        return userMedicationsRepository.findAll();
    }

    // Get UserMedications by Id
    public Optional<UserMedications> getUserMedicationsById(Integer id) {
        return userMedicationsRepository.findById(id);
    }

    // Create UserMedications
    public UserMedications createUserMedications(UserMedications userMedications) {
        if(userMedicationsRepository.findById(userMedications.getUsers().getId()).isEmpty()) {
            throw new RuntimeException("User id doesn't exist");
        }
        if(medicationsRepository.findById(userMedications.getMedications().getId()).isEmpty()) {
            throw new RuntimeException("Medications id doesn't exist");
        }
        return userMedicationsRepository.save(userMedications);
    }

    // Update UserMedications by ID
    public UserMedications updateUserMedications(Integer id, UserMedications updatedUserMedications) {
        if(userMedicationsRepository.findById(updatedUserMedications.getUsers().getId()).isEmpty()) {
            throw new RuntimeException("User id doesn't exist");
        }
        if(medicationsRepository.findById(updatedUserMedications.getMedications().getId()).isEmpty()) {
            throw new RuntimeException("Medications id doesn't exist");
        }
        return userMedicationsRepository.findById(id).map(userMedications -> {
                userMedications.setMedications(updatedUserMedications.getMedications());
                userMedications.setUsers(updatedUserMedications.getUsers());
                return userMedicationsRepository.save(userMedications);
        }).orElseThrow(() -> new RuntimeException("usermedication with ID " + id + " not found"));
    }

    public void deleteUserMedications(Integer id) {
        if (userMedicationsRepository.existsById(id)) {
            userMedicationsRepository.deleteById(id);
        } else {
            throw new RuntimeException("UserMedications with ID " + id + " not found");
        }
    }
}
