package dev.gestionpedidos.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    private LocalDateTime shippingDate;
    private String status;
    private String comment;
    private double total;

    // CONSTRUCTORS
    public Order() {
    }

    public Order(Customer customer, LocalDateTime orderDate, String status, String comment, double total) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.total = total;
    }

}