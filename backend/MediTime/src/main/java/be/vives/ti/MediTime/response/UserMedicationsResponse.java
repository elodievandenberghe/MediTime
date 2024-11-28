package be.vives.ti.MediTime.response;

import be.vives.ti.MediTime.domain.UserMedications;

import java.util.List;
import java.util.stream.Collectors;

public class UserMedicationsResponse {

    private Integer id;
    private Integer userId;
    private Integer medicationId;
    private List<MedicationScheduleResponse> schedule;

    public UserMedicationsResponse(UserMedications userMedications) {
        this.id = userMedications.getId();
        this.userId = userMedications.getUsers().getId();
        this.medicationId = userMedications.getMedications().getId();
        this.schedule = userMedications.getSchedule() != null
                ? userMedications.getSchedule().stream()
                .map(MedicationScheduleResponse::new)
                .collect(Collectors.toList())
                : null;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public List<MedicationScheduleResponse> getSchedule() {
        return schedule;
    }
}
