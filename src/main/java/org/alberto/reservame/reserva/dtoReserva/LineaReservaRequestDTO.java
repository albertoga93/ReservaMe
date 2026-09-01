package org.alberto.reservame.reserva.dtoReserva;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class LineaReservaRequestDTO {

    @NotNull(message = "Debe indicar la variante a reservar")
    private Long varianteId;

    @Positive(message = "La cantidad debe ser mayor que 0")
    private int cantidad;

    public LineaReservaRequestDTO() {
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
