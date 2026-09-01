package org.alberto.reservame.reserva.dtoReserva;

import org.alberto.reservame.reserva.Estado;

import java.time.LocalDateTime;

public class ComprobarReservaResponseDTO {

    private Estado estado;
    private LocalDateTime fechaRecogida;

    public ComprobarReservaResponseDTO() {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDateTime fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }
}
