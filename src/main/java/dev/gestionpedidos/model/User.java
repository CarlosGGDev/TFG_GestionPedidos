package dev.gestionpedidos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 9, nullable = false, unique = true)
    private String nif;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 70, nullable = false)
    private String address;

    private int zipcode;

    @Column(length = 50, nullable = false)
    private String town;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role = Role.ROLE_USER;

}