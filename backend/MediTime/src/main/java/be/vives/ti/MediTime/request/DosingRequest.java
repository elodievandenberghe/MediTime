package be.vives.ti.MediTime.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DosingRequest {

    @NotEmpty(message = "ID cannot be null")
    private Integer dosingId;
    @NotEmpty(message = "Dosing Type cannot be null")
    @Size(min = 1, max = 50, message = "Dosing Type must be between 1 and 50 characters")
    private String dosingType;

    public Integer getDosingId() { return dosingId; }
    public String getDosingType() {
        return dosingType;
    }

    public void setDosingType(String dosingType) {
        this.dosingType = dosingType;
    }

}
