package org.alberto.reservame.usuario.dtoUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CambiarPasswoodDTO {

    @NotBlank(message = "La contraseña actual no puede estar vacía")
    private String passwordActual;

    @NotBlank(message = "La contraseña actual no puede estar vacía")
    @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
    private String password;

    @NotBlank(message = "La contraseña actual no puede estar vacía")
    private String confirmarPassword;

    public CambiarPasswoodDTO(String passworActual, String password, String confirmarPasswor) {
        this.passwordActual = passworActual;
        this.password = password;
        this.confirmarPassword = confirmarPasswor;
    }

    public CambiarPasswoodDTO(){

    }

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passworActual) {
        this.passwordActual = passworActual;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPasswor(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }
}