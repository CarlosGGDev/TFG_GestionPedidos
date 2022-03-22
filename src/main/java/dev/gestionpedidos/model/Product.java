package dev.gestionpedidos.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 70, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private double price;

    // CONSTRUCTORS
    public Product() {
    }

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

}