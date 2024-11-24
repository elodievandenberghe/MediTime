package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Dosing")
public class Dosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="DosingType")
    private String dosingType;

    @OneToMany(mappedBy="Medications")
    private List<Medications> medications;
    
    public Dosing() {}
    public Dosing(String dosingType) {
        this.dosingType = dosingType;
    }
    public Integer getId() { return id; }
    public String getDosingType() { return dosingType; }
    public void setDosingType(String dosingType) { this.dosingType = dosingType; }
}
