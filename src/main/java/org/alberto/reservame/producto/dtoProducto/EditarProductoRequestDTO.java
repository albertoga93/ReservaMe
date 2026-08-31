package org.alberto.reservame.producto.dtoProducto;

import jakarta.validation.constraints.NotBlank;

public class EditarProductoRequestDTO {

    private String nombre;
    private String descripccion;
    private String categoria;
    private Boolean activo;

    public EditarProductoRequestDTO () {

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
