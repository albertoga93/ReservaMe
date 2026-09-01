package org.alberto.reservame.reserva.dtoReserva;

import org.alberto.reservame.cliente.dtoCliente.ClienteResponseDTO;
import org.alberto.reservame.reserva.Estado;
import org.alberto.reservame.usuario.dtoUsuario.EmpleadoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class DetalleReservaResponseDTO {

    private Long id;
    private String codigoPublico;
    private ClienteResponseDTO cliente;
    private LocalDateTime fechaRecogida;
    private Estado estado;
    private String notas;
    private EmpleadoResponseDTO empleado;
    private List<LineaReservaResponseDTO> listLineaReserva;

    public DetalleReservaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPublico() {
        return codigoPublico;
    }

    public void setCodigoPublico(String codigoPublico) {
        this.codigoPublico = codigoPublico;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EmpleadoResponseDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoResponseDTO empleado) {
        this.empleado = empleado;
    }

    public List<LineaReservaResponseDTO> getListLineaReserva() {
        return listLineaReserva;
    }

    public void setListLineaReserva(List<LineaReservaResponseDTO> listLineaReserva) {
        this.listLineaReserva = listLineaReserva;
    }
}
