package be.vives.ti.MediTime.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserMedicationsRequest {

    @NotEmpty(message = "User ID cannot be null")
    private Integer userId;

    @NotEmpty(message = "Medication ID cannot be null")
    private Integer medicationId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }
}
