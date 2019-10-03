package com.example.apteka.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="d_user")
public class UserEntity {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String login;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public UserEntity(Integer id,String login, String name, String surname, String password, String role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String login, String name, String surname, String password, String role) {
        id = 0;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return getLogin().equals(that.getLogin()) &&
                getName().equals(that.getName()) &&
                getSurname().equals(that.getSurname()) &&
                getPassword().equals(that.getPassword()) &&
                getRole().equals(that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getName(), getSurname(), getPassword(), getRole());
    }
}
