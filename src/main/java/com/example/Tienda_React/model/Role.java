// src/main/java/com/example/Tienda_React/model/Role.java

package com.example.Tienda_React.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public enum ERole {
        ROLE_ADMIN,
        ROLE_VENDEDOR,
        ROLE_CLIENTE
    }
}
