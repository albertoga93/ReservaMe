package org.alberto.reservame.reserva.dtoReserva;

import org.alberto.reservame.producto.dtoProducto.VarianteResponseDTO;

import java.math.BigDecimal;

public class LineaReservaResponseDTO {

    private int cantidad;
    private BigDecimal precioUnitario;
    private VarianteResponseDTO producto;

    public LineaReservaResponseDTO() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public VarianteResponseDTO getProducto() {
        return producto;
    }

    public void setProducto(VarianteResponseDTO producto) {
        this.producto = producto;
    }
}
