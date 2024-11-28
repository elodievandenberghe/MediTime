package be.vives.ti.MediTime.response;

import be.vives.ti.MediTime.domain.Users;

public class UserResponse {

    private Integer id;
    private String email;
    private String name;
    private String lastName;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastName = user.getLastName();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
