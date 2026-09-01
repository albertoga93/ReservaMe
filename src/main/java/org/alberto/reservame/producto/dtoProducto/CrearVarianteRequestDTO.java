package org.alberto.reservame.producto.dtoProducto;

import jakarta.validation.constraints.*;
import org.alberto.reservame.producto.UnidadMedida;

import java.math.BigDecimal;


public class CrearVarianteRequestDTO {

    @NotBlank(message = "El nombre de la variante no puede estar vacío")
    private String nombre;

    @NotNull(message = "La unidad de medida no puede estar vacía")
    private UnidadMedida unidadMedida;

    @Positive
    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @NotNull(message = "El Stock no puede ser nulo")
    private Integer stock;

    public CrearVarianteRequestDTO(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
