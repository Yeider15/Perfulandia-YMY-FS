package com.ymy.cl.perfulandiafs.ventas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ymy.cl.perfulandiafs.inventario.model.Perfume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Hacemos que cantidad sea obligatorio
    private Integer cantidad;

    @Column(nullable = false) // Hacemos que precioUnitario sea obligatorio con precisión
    private double precioUnitario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_venta", insertable = false, updatable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Perfume perfume; // Relación con Perfume


}
