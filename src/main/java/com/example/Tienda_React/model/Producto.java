package com.example.Tienda_React.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;  // mismo id que usas en el front

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String imagen; // opcional, si después quieres URL de imagen
}
