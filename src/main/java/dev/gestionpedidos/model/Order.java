package dev.gestionpedidos.model;

import java.util.List;
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

    @Column(name = "shipping_adress", length = 45)
    private String shippingAddress;

    @Column(length = 9, nullable = false)
    private String status;

    private String comment;

    // TOREV: una lista con los detalles por cada factura
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderDetail> orderDetails;

    private double total;

    // CONSTRUCTORS

    public Order() {
    }

    // TOREV: STATUS ES DE TIPO ENUM (clase propia)
    public Order(User user, LocalDateTime orderDate, String status, String comment, double total) {
        this.user = user;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.total = total;
    }

}