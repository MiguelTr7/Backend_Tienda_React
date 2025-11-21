package com.example.Tienda_React.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoResponse {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer categoriaId;
    private String descripcion;
    private String imagen;
}
