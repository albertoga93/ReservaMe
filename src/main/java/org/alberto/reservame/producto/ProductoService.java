package org.alberto.reservame.producto;

import org.alberto.reservame.exception.OperacionNoPermitidaException;
import org.alberto.reservame.exception.RecursoNoEncontradoException;
import org.alberto.reservame.producto.dtoProducto.*;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final VarianteRepository varianteRepository;

    public ProductoService(ProductoRepository productoRepository, VarianteRepository varianteRepository) {
        this.productoRepository = productoRepository;
        this.varianteRepository = varianteRepository;
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

    public VarianteResponseDTO crearVariante (Long id, CrearVarianteRequestDTO dto){

        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));

        if(!producto.isActivo()) {
            throw new OperacionNoPermitidaException("No se puede añadir una variante a un producto inactivo");
        }

        VarianteProducto variante = new VarianteProducto(
                dto.getNombre(),
                gererarSKU(),
                dto.getUnidadMedida(),
                dto.getPrecio(),
                dto.getStock(),
                producto
        );

        VarianteProducto varianteGuardada = varianteRepository.save(variante);

        return toVarianteDto(varianteGuardada);

    }

    public VarianteResponseDTO editarVariante(EditarVarianteRequestDTO dto, Long id){
        VarianteProducto variante = varianteRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Variante no encontrada"));

        if(dto.getNombre() == null
        && dto.getUnidadMedida() == null
        && dto.getPrecio() == null
        && dto.getStock() == null
        && dto.getActivo() == null){
            throw new IllegalArgumentException("Debe indicar almenos un campo a modificar");
        }

        if(dto.getNombre() != null && dto.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if(dto.getNombre() != null){
            variante.setName(dto.getNombre());
        }
        if(dto.getUnidadMedida() != null){
            variante.setUnidadMedida(dto.getUnidadMedida());
        }
        if(dto.getPrecio() != null){
            variante.setPrecio(dto.getPrecio());
        }
        if(dto.getStock()!= null){
            variante.setStock(dto.getStock());
        }
        if(dto.getActivo() != null){
            variante.setActivo(dto.getActivo());
        }

        VarianteProducto varianteGuardada = varianteRepository.save(variante);

        return toVarianteDto(varianteGuardada);
    }

    public List<ProductoResponseDTO> listarProductos(String nombre, String categoria) {
        List<Producto> productosList = productoRepository.findAll();

        List<ProductoResponseDTO> existList = productosList.stream()
                .filter(pr -> categoria == null ||
                        (pr.getCategoria() != null &&
                                pr.getCategoria().toLowerCase().contains(categoria.toLowerCase())))
                .filter(pr -> nombre == null ||
                        pr.getName().toLowerCase().contains(nombre.toLowerCase()))
                .map(pr -> this.toProductoDto(pr))
                .toList();

        return existList;
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
                variante.getFechaCreacion(),
                variante.getFechaActualizacion()
        );
        return dto;
    }
}
