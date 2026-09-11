package org.alberto.reservame.reserva.dtoReserva;

import org.alberto.reservame.cliente.dtoCliente.ClienteResponseDTO;
import org.alberto.reservame.reserva.Estado;

import java.time.LocalDateTime;

public class ReservaResponseDTO {

    private Long id;
    private ClienteResponseDTO cliente;
    private LocalDateTime fechaRecogida;
    private Estado estado;
    private int numeroLineas;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(Long id, ClienteResponseDTO cliente, LocalDateTime fechaRecogida, Estado estado, int numeroLineas) {
        this.id = id;
        this.cliente = cliente;
        this.fechaRecogida = fechaRecogida;
        this.estado = estado;
        this.numeroLineas = numeroLineas;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDateTime fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
