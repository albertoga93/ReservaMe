package org.alberto.reservame.reserva;

import jakarta.persistence.*;
import org.alberto.reservame.cliente.Cliente;
import org.alberto.reservame.usuario.Usuario;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
