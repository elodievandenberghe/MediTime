package be.vives.ti.MediTime.service;

import be.vives.ti.MediTime.domain.Users;
import be.vives.ti.MediTime.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public UserService() {}
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Users createUser(Users user) {
        if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return usersRepository.save(user);
        } else {
            throw new RuntimeException("User with email " + user.getEmail() + "  already exists");
        }
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
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }
}
