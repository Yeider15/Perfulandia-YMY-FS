package com.ymy.cl.perfulandiafs.inventario.repository;

import com.ymy.cl.perfulandiafs.inventario.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {
    // Buscar perfume por su nombre
    Perfume findByNombre(String nombre);
}