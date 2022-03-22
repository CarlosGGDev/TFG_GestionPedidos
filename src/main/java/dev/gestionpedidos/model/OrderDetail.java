package dev.gestionpedidos.model;

import javax.persistence.*;

@Entity
@Table(name = "orders_details")
public class OrderDetail {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double total;

    // CONSTRUCTORS
    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product, int quantity, double price, double total) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

}