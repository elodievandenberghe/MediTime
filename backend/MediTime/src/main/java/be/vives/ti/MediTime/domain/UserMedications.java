package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="user_medications")
public class UserMedications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "User cannot be null")  // Ensure the user is not null
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    @NotNull(message = "Medication cannot be null")  // Ensure the medication is not null
    private Medications medications;

    @OneToMany(mappedBy = "userMedications", cascade = CascadeType.ALL)
    private List<MedicationSchedule> schedule;

    public UserMedications() {}
    public UserMedications(Users users, Medications medications) {
        this.users = users;
        this.medications = medications;
    }

    public Integer getId() {
        return id;
    }
    public Users getUsers() { return users; }
    public void setUsers(Users users) { this.users = users; }
    public Medications getMedications() { return medications; }
    public void setMedications(Medications medications) { this.medications = medications; }
    public List<MedicationSchedule> getSchedule() { return schedule; }
    public void setSchedule(List<MedicationSchedule> schedule) { this.schedule = schedule; }

}
