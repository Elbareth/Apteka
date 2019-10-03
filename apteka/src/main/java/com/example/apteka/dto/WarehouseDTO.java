package com.example.apteka.dto;

import java.util.List;
import java.util.Objects;

public class WarehouseDTO {
    private String name;
    private Integer listOfMedicines;
    private Integer listOfUsers;

    public WarehouseDTO(String name, Integer listOfMedicines, Integer listOfUsers) {
        this.name = name;
        this.listOfMedicines = listOfMedicines;
        this.listOfUsers = listOfUsers;
    }

    public WarehouseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getListOfMedicines() {
        return listOfMedicines;
    }

    public void setListOfMedicines(Integer listOfMedicines) {
        this.listOfMedicines = listOfMedicines;
    }

    public Integer getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(Integer listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    public String toString() {
        return "WarehouseDTO{" +
                ", name='" + name + '\'' +
                ", listOfMedicines=" + listOfMedicines +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseDTO)) return false;
        WarehouseDTO that = (WarehouseDTO) o;
        return getName().equals(that.getName()) &&
                getListOfMedicines().equals(that.getListOfMedicines()) &&
                getListOfUsers().equals(that.getListOfUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getListOfMedicines(), getListOfUsers());
    }
}
