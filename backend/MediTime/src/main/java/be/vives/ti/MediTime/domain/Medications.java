package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "medications")
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Medication name cannot be null")
    @Size(min = 1, max = 50, message = "Medication name must be between 1 and 50 characters")
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull(message = "Category cannot be null")
    private Categories categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dosing_id", referencedColumnName = "id")
    @NotNull(message = "Dosing cannot be null")
    private Dosing dosing;

    public Medications() {}
    public Medications(String name, Categories categories, Dosing dosing) {
        this.name = name;
        this.categories = categories;
        this.dosing = dosing;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Categories getCategory() { return categories; }
    public void setCategory(Categories categories) { this.categories = categories; }
    public Dosing getDosing() { return dosing; }
    public void setDosing(Dosing dosing) { this.dosing = dosing; }
}
