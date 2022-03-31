package dev.gestionpedidos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    private LocalDateTime shippingDate;

    private String status;

    private String comment;

    private double total;

    // CONSTRUCTORS
    public Order() {
    }

    public Order(User user, LocalDateTime orderDate, String status, String comment, double total) {
        this.user = user;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.total = total;
    }

}