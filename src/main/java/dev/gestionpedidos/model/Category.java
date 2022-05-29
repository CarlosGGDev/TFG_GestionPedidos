package dev.gestionpedidos.model;

import lombok.*;

import javax.persistence.*;

/**
 * Category object.
 * Is a foreign key in Product object
 */
@Builder
@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 70, nullable = false)
    private String name;

}