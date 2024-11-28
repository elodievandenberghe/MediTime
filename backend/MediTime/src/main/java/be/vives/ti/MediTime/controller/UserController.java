package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Users;
import be.vives.ti.MediTime.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> findAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> findUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PostMapping
    public Users createUser(@Valid @RequestBody Users user){
        userService.createUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable("id") int id, @Valid @RequestBody Users updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
