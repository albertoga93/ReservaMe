package org.alberto.reservame.reserva.dtoReserva;

import jakarta.validation.constraints.NotNull;
import org.alberto.reservame.reserva.Estado;

public class EditarEstadoReservaRequestDTO {

    @NotNull
    private Estado estado;

    public EditarEstadoReservaRequestDTO() {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
