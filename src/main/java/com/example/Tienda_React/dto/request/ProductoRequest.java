package com.example.Tienda_React.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoRequest {
    private String nombre;
    private BigDecimal precio;
    private Integer categoriaId;
    private String descripcion;
    private String imagen;
}
