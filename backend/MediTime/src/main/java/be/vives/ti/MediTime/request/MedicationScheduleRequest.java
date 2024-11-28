package be.vives.ti.MediTime.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class MedicationScheduleRequest {

    @NotEmpty(message = "User Medication ID cannot be null")
    private Integer userMedicationId;

    @NotEmpty(message = "Week Mask cannot be null")
    private Byte weekMask;

    @NotEmpty(message = "Time cannot be null")
    private LocalTime time;

    public Integer getUserMedicationId() {
        return userMedicationId;
    }

    public void setUserMedicationId(Integer userMedicationId) {
        this.userMedicationId = userMedicationId;
    }

    public Byte getWeekMask() {
        return weekMask;
    }

    public void setWeekMask(Byte weekMask) {
        this.weekMask = weekMask;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
