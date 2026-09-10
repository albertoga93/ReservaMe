package org.alberto.reservame.producto;

import jakarta.validation.Valid;
import org.alberto.reservame.producto.dtoProducto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PreAuthorize("hasRole('DUENO')")
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody CrearProductoRequestDTO dto){

        ProductoResponseDTO creado = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);

    }


    @PreAuthorize("hasRole('DUENO')")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> editarProducto(@PathVariable Long id, @RequestBody EditarProductoRequestDTO dto){
        return ResponseEntity.ok(productoService.editarProducto(id,dto));
    }


    @PreAuthorize("hasRole('DUENO')")
    @PostMapping("/{id}/variantes")
    public ResponseEntity<VarianteResponseDTO> crearVariante(@PathVariable Long id,@Valid @RequestBody CrearVarianteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearVariante(id,dto));
    }

    @PreAuthorize("hasRole('DUENO')")
    @PatchMapping("/variantes/{id}")
    public ResponseEntity<VarianteResponseDTO> editarVariante(@RequestBody EditarVarianteRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(productoService.editarVariante(dto,id));
    }

    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @GetMapping

    public ResponseEntity<List<ProductoResponseDTO>> listarProductos(@RequestParam(required = false) String nombre, @RequestParam(required = false)String categoria){
        return ResponseEntity.ok(productoService.listarProductos(nombre, categoria));
    }



}
