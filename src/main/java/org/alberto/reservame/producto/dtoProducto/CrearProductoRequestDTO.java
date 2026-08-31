package org.alberto.reservame.producto.dtoProducto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public class CrearProductoRequestDTO {

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String nombre;

    private String descripccion;
    private String categoria;

    @NotEmpty(message = "Debe incluir al menos una variante")
    @Valid
    private List<CrearVarianteRequestDTO> variantes;

    public CrearProductoRequestDTO(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<CrearVarianteRequestDTO> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<CrearVarianteRequestDTO> variantes) {
        this.variantes = variantes;
    }
}
