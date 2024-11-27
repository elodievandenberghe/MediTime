package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

@Entity
@Table(name="Medications")
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @Column(name="Name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CateogryId", referencedColumnName = "Id")
    private Categories categories;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DosingId", referencedColumnName = "Id")
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
