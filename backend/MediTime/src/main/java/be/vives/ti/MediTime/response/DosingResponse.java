package be.vives.ti.MediTime.response;

import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.domain.Dosing;

public class DosingResponse {

    private Integer id;
    private String dosing;

    public DosingResponse(Dosing dosing) {
        this.id = dosing.getId();
        this.dosing = dosing.getDosingType();
    }

    public Integer getId() {
        return id;
    }

    public String getDosing() {
        return dosing;
    }
}