package com.example.apteka.dto;

import java.util.Objects;

public class LoginDTO {
    private String login;
    private String password;

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDTO)) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return getLogin().equals(loginDTO.getLogin()) &&
                getPassword().equals(loginDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
