package be.vives.ti.MediTime.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriesRequest {
    @NotEmpty(message = "ID cannot be null")
    private Integer categoryId;
    @NotEmpty(message = "Category cannot be null")
    @Size(min = 1, max = 50, message = "Category must be between 1 and 50 characters")
    private String category;

    public Integer getCategoryId() { return categoryId; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}