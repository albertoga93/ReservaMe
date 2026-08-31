package org.alberto.reservame.producto.dtoProducto;

import org.alberto.reservame.producto.UnidadMedida;

import java.math.BigDecimal;

public class EditarVarianteRequestDTO {

    private String nombre;
    private UnidadMedida unidadMedida;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;

    public EditarVarianteRequestDTO(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
