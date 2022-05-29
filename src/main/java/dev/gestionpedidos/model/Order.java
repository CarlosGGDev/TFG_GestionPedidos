package dev.gestionpedidos.model;

import java.util.List;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Order object.
 * Is a complex object wit a nested objects.
 * Contains a User and a list of Order Details objects.
 * Their relationships are mapped by JPA.
 */
@Builder
@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "shipping_adress", length = 45)
    private String shippingAddress;

    @Column(length = 9, nullable = false)
    private String status = "pendiente";

    private String comment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderDetail> orderDetails;

    private double total;

}