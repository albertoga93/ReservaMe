package org.alberto.reservame.producto.dtoProducto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private List<VarianteResponseDTO> variantes;

    public ProductoResponseDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<VarianteResponseDTO> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<VarianteResponseDTO> variantes) {
        this.variantes = variantes;
    }
}
