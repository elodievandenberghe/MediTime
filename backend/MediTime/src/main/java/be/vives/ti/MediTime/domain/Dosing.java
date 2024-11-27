package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "dosing")
public class Dosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @Column(name= "dosing_type")
    private String dosingType;

    @OneToMany(mappedBy="dosing")
    private List<Medications> medications;

    public Dosing() {}
    public Dosing(String dosingType) {
        this.dosingType = dosingType;
    }
    public Integer getId() { return id; }
    public String getDosingType() { return dosingType; }
    public void setDosingType(String dosingType) { this.dosingType = dosingType; }
}
