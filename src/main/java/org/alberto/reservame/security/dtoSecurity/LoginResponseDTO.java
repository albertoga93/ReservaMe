package org.alberto.reservame.security.dtoSecurity;

import org.alberto.reservame.usuario.Cargo;

public class LoginResponseDTO {

    private String token;
    private String nombre;
    private Cargo rol;


    public LoginResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cargo getRol() {
        return rol;
    }

    public void setRol(Cargo rol) {
        this.rol = rol;
    }
}
