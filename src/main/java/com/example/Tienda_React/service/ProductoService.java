package com.example.Tienda_React.service;

import com.example.Tienda_React.dto.request.ProductoRequest;
import com.example.Tienda_React.dto.response.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> listar();
    ProductoResponse obtenerPorId(Long id);
    ProductoResponse crear(ProductoRequest request);
    ProductoResponse actualizar(Long id, ProductoRequest request);
    void eliminar(Long id);
}
