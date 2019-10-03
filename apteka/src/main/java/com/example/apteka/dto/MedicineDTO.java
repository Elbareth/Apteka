package com.example.apteka.dto;

import java.time.LocalDate;
import java.util.Objects;

public class MedicineDTO {
    private String name;
    private LocalDate dateOfUse;
    private String description;
    private Float price;

    public MedicineDTO(String name, LocalDate dateOfUse, String description, Float price) {
        this.name = name;
        this.dateOfUse = dateOfUse;
        this.description = description;
        this.price = price;
    }

    public MedicineDTO() {
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
        return "MedicineDTO{" +
                ", name='" + name + '\'' +
                ", dateOfUse=" + dateOfUse +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicineDTO)) return false;
        MedicineDTO that = (MedicineDTO) o;
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
