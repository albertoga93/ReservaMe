package org.alberto.reservame.usuario.dtoUsuario;


import org.alberto.reservame.usuario.Cargo;

import java.time.LocalDateTime;

public class EmpleadoResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    public EmpleadoResponseDTO(){

    }

    public EmpleadoResponseDTO(Long id, String nombre, String email, String rol, boolean activo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
