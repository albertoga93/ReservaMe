package org.alberto.reservame.usuario;

import jakarta.persistence.*;
import org.alberto.reservame.reserva.Reserva;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cargo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo rol;

    @Column(name = "estado", nullable = false)
    private boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    //constructor vacio para hibernate
    protected Usuario(){

    }

    public Usuario(String nombre, String email, String password, Cargo rol, boolean activo){
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setRol(rol);
        setActivo(activo);
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cargo getRol() {
        return rol;
    }

    public void setRol(Cargo rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @PrePersist
    protected void onCreate(){
        this.fechaCreacion = LocalDateTime.now();
    }



    /*
    METODOS IMPLEMENTACION INTERFAZ UserDetail

     */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol.name()));
    }

    @Override
    public String getUsername(){
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.activo;
    }




}
