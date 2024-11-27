package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    @Column
    private String category;

    @OneToMany(mappedBy="categories")
    private List<Medications> medications;

    public Categories() {}
    public Categories(String category) {
        this.category = category;
    }
    public Integer getId() {return id;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

}
