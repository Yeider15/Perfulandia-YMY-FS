package com.ymy.cl.perfulandiafs.ventas.service;

import com.ymy.cl.perfulandiafs.ventas.model.Venta;
import com.ymy.cl.perfulandiafs.ventas.model.DetalleVenta;
import com.ymy.cl.perfulandiafs.ventas.repository.VentaRepository;
import com.ymy.cl.perfulandiafs.ventas.repository.DetalleVentaRepository;
import com.ymy.cl.perfulandiafs.inventario.repository.PerfumeRepository;
import com.ymy.cl.perfulandiafs.inventario.model.Perfume;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    public String crearVenta(Venta venta) {
        Venta nuevaVenta = ventaRepository.save(venta);

        // Guardar los detalles de la venta
        for (DetalleVenta detalle : venta.getDetalleVentas()) {
            detalle.setVenta(nuevaVenta);  // Asociamos el detalle a la venta
            detalleVentaRepository.save(detalle);  // Guardamos el detalle
            actualizarStock(detalle.getPerfume().getId(), detalle.getCantidad());  // Actualizamos el stock
        }

        return "Venta registrada con éxito";
    }

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public List<DetalleVenta> obtenerDetallesDeVentaID(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }

    public String eliminarVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada");
        }
        ventaRepository.deleteById(id);
        return "Venta anulada con éxito";
    }

    // Actualizar el stock de un perfume después de la venta
    private void actualizarStock(Integer perfumeId, int cantidadVendida) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new RuntimeException("Perfume no encontrado"));
        int nuevoStock = perfume.getStock() - cantidadVendida;

        if (nuevoStock < 0) {
            throw new RuntimeException("No hay suficiente stock para el perfume: " + perfume.getNombre());
        }

        perfume.setStock(nuevoStock);
        perfumeRepository.save(perfume);  // Guardamos el perfume actualizado
    }

}
