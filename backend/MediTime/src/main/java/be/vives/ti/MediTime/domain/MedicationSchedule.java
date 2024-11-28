package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
    @Table(name= "medication_schedule")
    public class MedicationSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_medication_id", referencedColumnName = "id")
    @NotNull(message = "User Medication cannot be null")  // Ensure the user medication is not null
    private UserMedications userMedications;

    @Column(name = "week_mask")
    @NotNull(message = "Week Mask cannot be null")  // Ensure the week mask is not null
    private Byte weekMask;

    @Column(name = "time")
    @NotNull(message = "Time cannot be null")  // Ensure the time is not null
    private LocalTime time;

        public MedicationSchedule() {}
        public MedicationSchedule(UserMedications userMedications, LocalTime time, Byte weekMask) {
            this.userMedications = userMedications;
            this.time = time;
            this.weekMask = weekMask;
        }

        public Integer getId() { return id; }
        public UserMedications getUserMedications() { return userMedications; }
        public void setUserMedications(UserMedications userMedications) { this.userMedications = userMedications; }
        public LocalTime getTime() { return time; }
        public void setTime(LocalTime time) { this.time = time; }
        public Byte getWeekMask() { return weekMask; }
        public void setWeekMask(Byte weekMask) { this.weekMask = weekMask; }
    }
