package com.example.apteka.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="d_medicine")
public class MedicineEntity {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    @Column(name="date_of_use")
    private LocalDate dateOfUse;
    @NotNull
    private String description;
    @NotNull
    private Float price;

    public MedicineEntity(Integer id, String name, LocalDate dateOfUse, String description, Float price) {
        this.id = id;
        this.name = name;
        this.dateOfUse = dateOfUse;
        this.description = description;
        this.price = price;
    }

    public MedicineEntity(String name, LocalDate dateOfUse, String description, Float price) {
        id = 0;
        this.name = name;
        this.dateOfUse = dateOfUse;
        this.description = description;
        this.price = price;
    }

    public MedicineEntity() {
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

    public LocalDate getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(LocalDate dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MedicineEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfUse=" + dateOfUse +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicineEntity)) return false;
        MedicineEntity that = (MedicineEntity) o;
        return getName().equals(that.getName()) &&
                getDateOfUse().equals(that.getDateOfUse()) &&
                getDescription().equals(that.getDescription()) &&
                getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateOfUse(), getDescription(), getPrice());
    }
}
