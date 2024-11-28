package be.vives.ti.MediTime.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicationsRequest {

    @NotEmpty(message = "Medication name cannot be null")
    @Size(min = 1, max = 50, message = "Medication name must be between 1 and 50 characters")
    private String name;

    @NotEmpty(message = "Category cannot be null")
    private Integer categoryId;

    @NotEmpty(message = "Dosing cannot be null")
    private Integer dosingId;

    // Constructors, Getters, Setters

    public MedicationsRequest(String name, Integer categoryId, Integer dosingId) {
        this.name = name;
        this.categoryId = categoryId;
        this.dosingId = dosingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDosingId() {
        return dosingId;
    }

    public void setDosingId(Integer dosingId) {
        this.dosingId = dosingId;
    }
}

