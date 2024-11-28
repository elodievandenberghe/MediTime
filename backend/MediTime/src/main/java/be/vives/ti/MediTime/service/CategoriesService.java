package be.vives.ti.MediTime.service;


import be.vives.ti.MediTime.domain.Categories;
import be.vives.ti.MediTime.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    @Autowired
    private CategoryRepository categoriesRepository;

    public CategoriesService() {}
    public Page<Categories> getAllCategories(Pageable pageable) {
        return categoriesRepository.findAll(pageable);
    }
    public Optional<Categories> getCategoryById(Integer id) {
        return categoriesRepository.findById(id);
    }

    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(Integer id, Categories updatedCategory) {
        return categoriesRepository.findById(id).map(category -> {
            category.setCategory(updatedCategory.getCategory());
            return categoriesRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));
    }

    public void deleteCategory(Integer id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category with ID " + id + " not found");
        }
    }
}