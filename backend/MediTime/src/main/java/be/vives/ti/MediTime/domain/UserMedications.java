package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

@Entity
@Table(name="UserMedicatons")
public class UserMedications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="UserId", referencedColumnName = "Id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="MedicationId", referencedColumnName = "Id")
    private Medications medications;

    public UserMedications() {}
    public UserMedications(Integer id, Users users, Medications medications) {
        this.id = id;
        this.users = users;
        this.medications = medications;
    }
}
