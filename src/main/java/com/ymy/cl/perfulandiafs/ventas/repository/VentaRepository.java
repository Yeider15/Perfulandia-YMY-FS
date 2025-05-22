package com.ymy.cl.perfulandiafs.ventas.repository;

import com.ymy.cl.perfulandiafs.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // Buscar ventas por estado
    List<Venta> findByEstado(String estado);
}
