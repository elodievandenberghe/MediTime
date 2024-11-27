package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user_medications")
public class UserMedications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medication_id", referencedColumnName = "id")
    private Medications medications;
    @OneToMany(mappedBy="userMedications", cascade = CascadeType.ALL)
    private List<MedicationSchedule> schedule;

    public UserMedications() {}
    public UserMedications(Integer id, Users users, Medications medications) {
        this.id = id;
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
