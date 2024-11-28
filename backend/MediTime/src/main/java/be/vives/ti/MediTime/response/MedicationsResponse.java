package be.vives.ti.MediTime.response;

import be.vives.ti.MediTime.domain.Medications;

public class MedicationsResponse {

    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer dosingId;

    public MedicationsResponse(Medications medication) {
        this.id = medication.getId();
        this.name = medication.getName();
        this.categoryId = medication.getCategory().getId();
        this.dosingId = medication.getDosing().getId();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getDosingId() {
        return dosingId;
    }
}

