package com.example.apteka.dto;

import java.util.List;
import java.util.Objects;

public class WarehouseCreateDTO {
    private String name;
    private List<Integer> listOfMedicines;
    private List<Integer> listOfUsers;

    public WarehouseCreateDTO(String name, List<Integer> listOfMedicines, List<Integer> listOfUsers) {
        this.name = name;
        this.listOfMedicines = listOfMedicines;
        this.listOfUsers = listOfUsers;
    }

    public WarehouseCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getListOfMedicines() {
        return listOfMedicines;
    }

    public void setListOfMedicines(List<Integer> listOfMedicines) {
        this.listOfMedicines = listOfMedicines;
    }

    public List<Integer> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<Integer> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    public String toString() {
        return "WarehouseCreateDTO{" +
                "name='" + name + '\'' +
                ", listOfMedicines=" + listOfMedicines +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseCreateDTO)) return false;
        WarehouseCreateDTO that = (WarehouseCreateDTO) o;
        return getName().equals(that.getName()) &&
                getListOfMedicines().equals(that.getListOfMedicines()) &&
                getListOfUsers().equals(that.getListOfUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getListOfMedicines(), getListOfUsers());
    }
}
