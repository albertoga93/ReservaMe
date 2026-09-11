package org.alberto.reservame.producto;

import org.alberto.reservame.producto.dtoProducto.ProductoResponseDTO;
import org.alberto.reservame.producto.dtoProducto.VarianteResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoMapper {

    //para convertir una instancia de producto en una responseProducto
    public ProductoResponseDTO toProductoDto(Producto producto){
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
    public VarianteResponseDTO toVarianteDto(VarianteProducto variante){
        VarianteResponseDTO dto = new VarianteResponseDTO(
                variante.getId(),
                variante.getName(),
                variante.getUnidadMedida(),
                variante.getSku(),
                variante.getPrecio(),
                variante.getStock(),
                variante.isActivo(),
                variante.getFechaCreacion(),
                variante.getFechaActualizacion()
        );
        return dto;
    }
}
