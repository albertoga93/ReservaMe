package org.alberto.reservame.reserva.dtoReserva;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.alberto.reservame.cliente.dtoCliente.ClienteRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CrearReservaRequestDTO {

    @NotNull(message = "Los datos del cliente son obligatorios")
    @Valid
    private ClienteRequestDTO cliente;

    @Future
    @NotNull
    private LocalDateTime fechaRecogida;

    private String notas;

    @NotEmpty(message = "Debe existir al menos una linea de reserva")
    @Valid
    private List<LineaReservaRequestDTO> listLineaReserva;

    public CrearReservaRequestDTO() {
    }

    public ClienteRequestDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteRequestDTO cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDateTime fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public List<LineaReservaRequestDTO> getListLineaReserva() {
        return listLineaReserva;
    }

    public void setListLineaReserva(List<LineaReservaRequestDTO> listLineaReserva) {
        this.listLineaReserva = listLineaReserva;
    }
}
