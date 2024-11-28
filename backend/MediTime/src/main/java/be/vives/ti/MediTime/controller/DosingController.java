package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Dosing;
import be.vives.ti.MediTime.exceptions.ResourceNotFoundException;
import be.vives.ti.MediTime.request.DosingRequest;
import be.vives.ti.MediTime.response.CategoriesResponse;
import be.vives.ti.MediTime.response.DosingResponse;
import be.vives.ti.MediTime.service.DosingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/dosing")
@RestController
public class DosingController {

    private final DosingService dosingService;

    @Autowired
    public DosingController(DosingService dosingService) {
        this.dosingService = dosingService;
    }

    // Get all dosing records
    @GetMapping
    public Page<DosingResponse> getAllDosing(Pageable pageable) {
        return this.dosingService.getAllDosing(pageable).map(DosingResponse::new);
    }

    // Get dosing by ID
    @GetMapping("/{id}")
    public DosingResponse getDosingById(@PathVariable("id") Integer id) {
        return new DosingResponse(dosingService.getDosingById(id).orElseThrow(() -> new ResourceNotFoundException(id, "dosing")));
    }

    // Create new dosing
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createDosing(@RequestBody @Valid DosingRequest dosingRequest) {
        Dosing createdDosing = dosingService.createDosing(new Dosing(
                dosingRequest.getDosingType()
        ));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDosing.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // Update dosing by ID
    @PutMapping("/{id}")
    public ResponseEntity<DosingResponse> updateDosing(
            @PathVariable("id") Integer id,
            @RequestBody @Valid DosingRequest updatedDosingRequest) {

        Dosing updatedDosing = dosingService.updateDosing(
              id, dosingService.getDosingById(updatedDosingRequest.getDosingId()).orElseThrow(() -> new ResourceNotFoundException(updatedDosingRequest.getDosingId().toString())));
        return new ResponseEntity<>(new DosingResponse(updatedDosing), HttpStatus.OK);
    }

    // Delete dosing by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDosing(@PathVariable("id") Integer id) {
        dosingService.deleteDosing(id);
    }
}


