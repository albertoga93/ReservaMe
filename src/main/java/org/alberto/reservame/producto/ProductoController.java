package org.alberto.reservame.producto;

import jakarta.validation.Valid;
import org.alberto.reservame.producto.dtoProducto.CrearProductoRequestDTO;
import org.alberto.reservame.producto.dtoProducto.EditarProductoRequestDTO;
import org.alberto.reservame.producto.dtoProducto.ProductoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}
