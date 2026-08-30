package org.alberto.reservame.cliente;

import jakarta.persistence.*;
import org.alberto.reservame.reserva.Reserva;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name= "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservasCliente;



    //constructor vacio para hibernate
    protected Cliente(){

    }

    public Cliente(String nombre, String email, String telefono){
        setNombre(nombre);
        setEmail(email);
        setTelefono(telefono);
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservasCliente() {
        return reservasCliente;
    }
}
