package dev.gestionpedidos.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 9, nullable = false)
    private String nif;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 70, nullable = false)
    private String adress;

    private int zipcode;

    @Column(length = 50, nullable = false)
    private String town;

    @Column(length = 100, nullable = false)
    private String password;

    // CONSTRUCTORS
    public Customer() {
    }

    public Customer(String nif, String name, String email, String phone, String adress, int zipcode, String town, String password) {
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.zipcode = zipcode;
        this.town = town;
        this.password = password;
    }

}