package org.alberto.reservame.cliente.dtoCliente;

import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;

    public ClienteRequestDTO() {
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
