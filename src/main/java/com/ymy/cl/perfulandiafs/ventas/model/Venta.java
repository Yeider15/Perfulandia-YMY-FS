package com.ymy.cl.perfulandiafs.ventas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ymy.cl.perfulandiafs.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false)  // Total obligatorio con precisión
    private double total;

    @Column(nullable = false, length = 20)  // Estado obligatorio
    private String estado;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "run_usuario", referencedColumnName = "id") // Relación con Usuario
    private Usuario usuario; // Un usuario realiza una venta

    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

}