package be.vives.ti.MediTime.response;
import java.time.LocalTime;
import be.vives.ti.MediTime.domain.MedicationSchedule;


public class MedicationScheduleResponse {

    private Integer id;
    private LocalTime time;
    private Byte weekMask;

    public MedicationScheduleResponse(MedicationSchedule medicationSchedule) {
        this.id = medicationSchedule.getId();
        this.time = medicationSchedule.getTime();
        this.weekMask = medicationSchedule.getWeekMask();
    }

    public Integer getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public Byte getWeekMask() {
        return weekMask;
    }
}

