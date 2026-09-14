package org.alberto.reservame.reserva.dtoReserva;

import jakarta.validation.constraints.NotBlank;

public class RecogerReservaRequestDTO {

    @NotBlank(message = "El codigo no puede estar vacio")
    private String codigoPublico;

    public RecogerReservaRequestDTO(String codigoPublico) {
        this.codigoPublico = codigoPublico;
    }

    public RecogerReservaRequestDTO() {
    }

    public String getCodigoPublico() {
        return codigoPublico;
    }

    public void setCodigoPublico(String codigoPublico) {
        this.codigoPublico = codigoPublico;
    }
}
