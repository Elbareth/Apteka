package com.example.apteka.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="d_warehouse")
public class WarehouseEntity {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="list_of_medicines", referencedColumnName = "id")
    private MedicineEntity listOfMedicines;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="list_of_users", referencedColumnName = "id")
    private UserEntity listOfUsers;

    public WarehouseEntity(Integer id, String name, MedicineEntity listOfMedicines, UserEntity listOfUsers) {
        this.id = id;
        this.name = name;
        this.listOfMedicines = listOfMedicines;
        this.listOfUsers = listOfUsers;
    }

    public WarehouseEntity(String name, MedicineEntity listOfMedicines, UserEntity listOfUsers) {
        id = 0;
        this.name = name;
        this.listOfMedicines = listOfMedicines;
        this.listOfUsers = listOfUsers;
    }

    public WarehouseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicineEntity getListOfMedicines() {
        return listOfMedicines;
    }

    public void setListOfMedicines(MedicineEntity listOfMedicines) {
        this.listOfMedicines = listOfMedicines;
    }

    public UserEntity getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(UserEntity listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    public String toString() {
        return "WarehouseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listOfMedicines=" + listOfMedicines +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseEntity)) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return getName().equals(that.getName()) &&
                getListOfMedicines().equals(that.getListOfMedicines()) &&
                getListOfUsers().equals(that.getListOfUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getListOfMedicines(), getListOfUsers());
    }
}
