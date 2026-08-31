package org.alberto.reservame.producto;


import jakarta.persistence.*;
import org.alberto.reservame.reserva.LineaReserva;

import java.util.List;

@Entity
@Table(name = "variante_producto")
public class VarianteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;





    @OneToMany(mappedBy = "producto")
    private List<LineaReserva> lineasReserva;


}
