package be.vives.ti.MediTime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name= "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    @Column(name= "email", unique = true)
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @Column(name= "name", nullable = false)
    private String name;
    @Column(name= "last_name", nullable = false)
    private String lastName;
    @Column(name= "password", nullable = false)
    private String password;
    @OneToMany(mappedBy="users", cascade = CascadeType.ALL)
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
