package org.alberto.reservame.reserva;

import jakarta.persistence.*;
import org.alberto.reservame.cliente.Cliente;
import org.alberto.reservame.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_publico", unique = true, nullable = false)
    private String idPublico;

    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_recogida")
    private LocalDateTime fechaRecogida;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "notas")
    private String notas;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "reserva")
    private List<LineaReserva> lineasReserva;


    //Constructor vacio para hibernate
    protected Reserva (){

    }

    public Reserva(LocalDateTime fecharecogida, Estado estado, String notas, Cliente cliente, Usuario usuario){
        setCliente(cliente);
        setUsuario(usuario);
        setFechaRecogida(fecharecogida);
        setNotas(notas);
        this.estado = Estado.PENDIENTE;
    }

    public String getIdPublico() {
        return idPublico;
    }

    public void setIdPublico(String idPublico) {
        this.idPublico = idPublico;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDateTime fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public List<LineaReserva> getLineasReserva() {
        return lineasReserva;
    }

    @PrePersist
    protected void onPersist(){
        this.fechaReserva = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }
}
