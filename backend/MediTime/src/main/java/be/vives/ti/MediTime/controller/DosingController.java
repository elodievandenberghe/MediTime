package be.vives.ti.MediTime.controller;

import be.vives.ti.MediTime.domain.Dosing;
import be.vives.ti.MediTime.service.DosingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("dosing")
@RestController
public class DosingController {

    private final DosingService dosingService;

    @Autowired
    public DosingController(DosingService dosingService) {
        this.dosingService = dosingService;
    }

    @GetMapping
    public List<Dosing> findAllDosing() {
        return dosingService.getAllDosing();
    }

    @GetMapping("/{id}")
    public Optional<Dosing> findDosingById(@PathVariable("id") Integer id) {
        return dosingService.getDosingById(id);
    }

    @PostMapping
    public Dosing createDosing(@Valid @RequestBody Dosing dosing) {
        return dosingService.createDosing(dosing);
    }

    @PutMapping("/{id}")
    public Dosing updateDosing(@PathVariable("id") Integer id,
                                               @Valid @RequestBody Dosing updatedDosing) {
        return dosingService.updateDosing(id, updatedDosing);
    }

    @DeleteMapping("/{id}")
    public void deleteDosing(@PathVariable("id") Integer id) {
        dosingService.deleteDosing(id);
    }
}

