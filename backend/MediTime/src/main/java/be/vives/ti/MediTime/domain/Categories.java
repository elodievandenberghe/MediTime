package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @Column
    private String category;

    @OneToMany(mappedBy="Medications")
    private List<Medications> medications;

    public Categories() {}
    public Categories(String category) {
        this.category = category;
    }
    public Integer getId() {return id;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

}
