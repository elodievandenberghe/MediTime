package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

    @Entity
    @Table(name= "categories")
    public class Categories {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @NotNull(message = "Category cannot be null")
        @Size(min = 1, max = 50, message = "Category must be between 1 and 50 characters")
        @Column
        private String category;

        @OneToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
        private List<Medications> medications;

        // Default constructor
        public Categories() {}

        // Constructor with category parameter
        public Categories(String category) {
            this.category = category;
        }

        // Getters and setters
        public Integer getId() {
            return id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
