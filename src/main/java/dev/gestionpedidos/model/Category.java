package dev.gestionpedidos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter @Setter
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

}