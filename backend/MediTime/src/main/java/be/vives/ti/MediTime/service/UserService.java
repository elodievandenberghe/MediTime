package be.vives.ti.MediTime.service;

import be.vives.ti.MediTime.domain.Users;
import be.vives.ti.MediTime.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public UserService() {}
    public Page<Users> getAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Integer id, Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
                user.setEmail(updatedUser.getEmail());
                user.setEmail(updatedUser.getEmail());
                user.setName(updatedUser.getName());
                user.setLastName(updatedUser.getLastName());
                user.setPassword(updatedUser.getPassword());
                return usersRepository.save(user);
            } else {
                throw new RuntimeException("User with email " + user.getEmail() + "  already exists");
            }

        }).orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
