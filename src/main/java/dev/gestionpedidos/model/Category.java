package dev.gestionpedidos.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 70, nullable = false)
    private String name;

    // CONSTRUCTORS
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

}