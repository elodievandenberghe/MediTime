package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name= "dosing")
public class Dosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Dosing Type cannot be null")
    @Size(min = 1, max = 50, message = "Dosing Type must be between 1 and 50 characters")
    @Column(name = "dosing_type")
    private String dosingType;

    @OneToMany(mappedBy = "dosing")
    private List<Medications> medications;

    // Default constructor
    public Dosing() {}

    // Constructor with dosingType parameter
    public Dosing(String dosingType) {
        this.dosingType = dosingType;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public String getDosingType() {
        return dosingType;
    }

    public void setDosingType(String dosingType) {
        this.dosingType = dosingType;
    }
}

