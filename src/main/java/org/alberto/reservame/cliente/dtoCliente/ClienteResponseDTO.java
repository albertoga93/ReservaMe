package org.alberto.reservame.cliente.dtoCliente;

public class ClienteResponseDTO {

    private String name;
    private String email;
    private String telefono;

    public ClienteResponseDTO() {

    }

    public ClienteResponseDTO(String name, String email, String telefono) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
