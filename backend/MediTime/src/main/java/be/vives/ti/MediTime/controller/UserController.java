package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Users;
import be.vives.ti.MediTime.exceptions.ResourceNotFoundException;
import be.vives.ti.MediTime.request.UserRequest;
import be.vives.ti.MediTime.response.MedicationScheduleResponse;
import be.vives.ti.MediTime.response.UserResponse;
import be.vives.ti.MediTime.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all medication schedules
    @GetMapping
    public Page<UserResponse> getAllCategories(Pageable pageable) {
        return this.userService.getAllUsers(pageable).map(UserResponse::new);
    }

    // Get dosing by ID
    @GetMapping("/{id}")
    public UserResponse getDosingById(@PathVariable("id") Integer id) {
        return new UserResponse(userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException(id, "dosing")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest) {
        Users newUser = userService.createUser(new Users(userRequest.getName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") int id, @Valid @RequestBody UserRequest updatedUserRequest) {
        Users user = new Users(updatedUserRequest.getName(), updatedUserRequest.getLastName(), updatedUserRequest.getEmail(), updatedUserRequest.getPassword());
        Users updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(new UserResponse(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
