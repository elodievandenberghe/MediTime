package be.vives.ti.MediTime.controller;
import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("categories")
@RestController
public class CategoryController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Categories> findAllCategories() {
        return categoriesService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Categories> findCategoryById(@PathVariable("id") Integer id) {
        return categoriesService.getCategoryById(id);
    }

    @PostMapping
    public Categories createCategory(@RequestBody Categories category) {
        return categoriesService.createCategory(category);
    }

    @PutMapping("/{id}")
    public Categories updateCategory(@PathVariable("id") Integer id,
                                                     @RequestBody Categories updatedCategory) {
        return categoriesService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
       categoriesService.deleteCategory(id);
    }
}
