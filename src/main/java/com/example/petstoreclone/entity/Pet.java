package com.example.petstoreclone.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Category category;
    @NotBlank(message = "Field cant be empty")
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Pet() {
    }

    public Pet(long id, Category category, String name, Status status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
