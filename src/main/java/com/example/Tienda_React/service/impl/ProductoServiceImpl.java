package com.example.Tienda_React.service.impl;

import com.example.Tienda_React.dto.request.ProductoRequest;
import com.example.Tienda_React.dto.response.ProductoResponse;
import com.example.Tienda_React.model.Producto;
import com.example.Tienda_React.repository.ProductoRepository;
import com.example.Tienda_React.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private ProductoResponse mapToResponse(Producto p) {
        ProductoResponse r = new ProductoResponse();
        r.setId(p.getId());
        r.setNombre(p.getNombre());
        r.setPrecio(p.getPrecio());
        r.setCategoriaId(p.getCategoriaId());
        r.setDescripcion(p.getDescripcion());
        r.setImagen(p.getImagen());
        return r;
    }

    private void mapRequestToEntity(ProductoRequest req, Producto p) {
        p.setNombre(req.getNombre());
        p.setPrecio(req.getPrecio());
        p.setCategoriaId(req.getCategoriaId());
        p.setDescripcion(req.getDescripcion());
        p.setImagen(req.getImagen());
    }

    @Override
    public List<ProductoResponse> listar() {
        return productoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductoResponse obtenerPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToResponse(p);
    }

    @Override
    public ProductoResponse crear(ProductoRequest request) {
        Producto p = new Producto();
        mapRequestToEntity(request, p);
        return mapToResponse(productoRepository.save(p));
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        mapRequestToEntity(request, p);
        return mapToResponse(productoRepository.save(p));
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
