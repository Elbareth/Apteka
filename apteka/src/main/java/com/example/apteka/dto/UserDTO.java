package com.example.apteka.dto;

import java.util.Objects;

public class UserDTO {
    private String login;
    private String name;
    private String surname;
    private String password;
    private String role;

    public UserDTO(String login, String name, String surname, String password, String role) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
    }

    public UserDTO() {
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

    @Override
    public String toString() {
        return "UserDTO{" +
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
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getLogin().equals(userDTO.getLogin()) &&
                getName().equals(userDTO.getName()) &&
                getSurname().equals(userDTO.getSurname()) &&
                getPassword().equals(userDTO.getPassword()) &&
                getRole().equals(userDTO.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getName(), getSurname(), getPassword(), getRole());
    }
}
