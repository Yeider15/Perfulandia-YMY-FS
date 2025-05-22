package com.ymy.cl.perfulandiafs.inventario.controller;

import com.ymy.cl.perfulandiafs.inventario.service.PerfumeService;
import com.ymy.cl.perfulandiafs.inventario.model.Perfume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public ResponseEntity<List<Perfume>> listarProductos() {
        List<Perfume> perfumes = perfumeService.findAll();
        if (perfumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(perfumes);  // 204 No Content
        }
        return ResponseEntity.ok(perfumes);  // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> obtenerProductoPorId(@PathVariable Integer id) {
        try {
            Perfume perfume = perfumeService.findById(id);
            return ResponseEntity.ok(perfume);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody Perfume perfume) {
        try {
            String mensaje = perfumeService.save(perfume);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);  // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());  // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) {
        try {
            String mensaje = perfumeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfume no encontrado");  // 404 Not Found
        }
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<String> actualizarStock(@PathVariable Integer id, @RequestParam int cantidadVendida) {
        try {
            String mensaje = perfumeService.updateStock(id, cantidadVendida);
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 400 Bad Request
        }
    }


}

