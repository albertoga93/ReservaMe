package org.alberto.reservame.producto;

import org.alberto.reservame.exception.RecursoNoEncontradoException;
import org.alberto.reservame.producto.dtoProducto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoResponseDTO crearProducto(CrearProductoRequestDTO dto){

        Producto producto = new Producto(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setDescripcion(dto.getDescripcion());


        for(CrearVarianteRequestDTO varianteDTO : dto.getVariantes()){

            String skuGenerado = gererarSKU();

            VarianteProducto variante = new VarianteProducto(
                    varianteDTO.getNombre(),
                    skuGenerado,
                    varianteDTO.getUnidadMedida(),
                    varianteDTO.getPrecio(),
                    varianteDTO.getStock(),
                    producto);

            producto.getVariantesProducto().add(variante);
        }

        Producto productoGuardado = productoRepository.save(producto);

        return toProductoDto(productoGuardado);

    }


    public ProductoResponseDTO editarProducto(Long id, EditarProductoRequestDTO dto){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));

        if(dto.getNombre() == null
        && dto.getActivo() == null
        && dto.getCategoria() == null
        && dto.getDescripcion() == null){
            throw new IllegalArgumentException("Debe indicar almenos un campo a modificar");
        }

        if(dto.getNombre() != null && dto.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if(dto.getNombre() != null){
            producto.setName(dto.getNombre());
        }

        if(dto.getDescripcion() != null){
            producto.setDescripcion(dto.getDescripcion());
        }

        if(dto.getCategoria() != null){
            producto.setCategoria(dto.getCategoria());
        }

        if(dto.getActivo() != null){
            producto.setActivo(dto.getActivo());

            if(!dto.getActivo()){
                for(VarianteProducto variante : producto.getVariantesProducto()){
                    variante.setActivo(false);
                }
            }
        }

        Producto productoGuardado = productoRepository.save(producto);

        return toProductoDto(productoGuardado);

    }





    //metodo para generar los SKU

    private String gererarSKU (){
        return "VAR-" +UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }


    //para convertir una instancia de producto en una responseProducto
    private ProductoResponseDTO toProductoDto(Producto producto){
        ProductoResponseDTO dto = new ProductoResponseDTO(
                producto.getId(),
                producto.getName(),
                producto.getDescripcion(),
                producto.getCategoria(),
                producto.isActivo(),
                producto.getFechaCreacion());

        List<VarianteResponseDTO> variantesDTO = producto.getVariantesProducto().stream().
                map(variante -> this.toVarianteDto(variante))
                .toList();
        dto.setVariantes(variantesDTO);

        return dto;
    }


    //Para convertir una instancia de variante en una VarianteResponseDto
    private VarianteResponseDTO toVarianteDto(VarianteProducto variante){
        VarianteResponseDTO dto = new VarianteResponseDTO(
                variante.getId(),
                variante.getName(),
                variante.getUnidadMedida(),
                variante.getSku(),
                variante.getPrecio(),
                variante.getStock(),
                variante.isActivo(),
                variante.getFechaCreacion()
        );
        return dto;
    }
}
