package org.alberto.reservame.usuario.dtoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CrearEmpleadoRequestDTO {

    @NotBlank(message = "El nombre del empleado no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El email del empleado no puede estar vacio")
    @Email(message = "El email no es valido")
    private String email;

    @Size(
            min = 8,
            max = 15,
            message = "La contraseña debe tener entre 8 y 15 caracteres")
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String confirmarPassword;

    public CrearEmpleadoRequestDTO(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaswwor1() {
        return confirmarPassword;
    }

    public void setPaswwor1(String paswwor1) {
        this.confirmarPassword = paswwor1;
    }
}
