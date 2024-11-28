package be.vives.ti.MediTime.response;

import be.vives.ti.MediTime.domain.Categories;

public class CategoriesResponse {

    private Integer id;
    private String category;

    public CategoriesResponse(Categories categories) {
        this.id = categories.getId();
        this.category = categories.getCategory();
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}