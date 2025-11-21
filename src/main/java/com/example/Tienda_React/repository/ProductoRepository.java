package com.example.Tienda_React.repository;

import com.example.Tienda_React.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
