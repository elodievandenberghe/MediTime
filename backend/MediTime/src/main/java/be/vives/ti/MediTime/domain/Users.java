package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @Column(name="Email")
    private String email;
    @Column(name="Name")
    private String name;
    @Column(name="LastName")
    private String lastName;
    @Column(name="Password")
    private String password;
    @OneToMany(mappedBy="Users")
    private List<UserMedications> medications;

    public Users() {}
    public Users(String email, String name, String lastName, String password) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public Integer getId() {return id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}
