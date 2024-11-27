package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

@Entity
@Table(name= "medications")
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    @Column(name= "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Categories categories;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "dosing_id", referencedColumnName = "id")
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
