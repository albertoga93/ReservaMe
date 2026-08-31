package org.alberto.reservame.reserva;

import jakarta.persistence.*;
import org.alberto.reservame.producto.VarianteProducto;

import java.math.BigDecimal;

@Entity
@Table(name = "linea_reserva")
public class LineaReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private VarianteProducto producto;


    protected LineaReserva(){

    }

    public LineaReserva(int cantidad,BigDecimal precioUnitario,  Reserva reserva, VarianteProducto producto){
        setCantidad(cantidad);
        setPrecioUnitario(precioUnitario);
        setProducto(producto);
        setReserva(reserva);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public VarianteProducto getProducto() {
        return producto;
    }

    public void setProducto(VarianteProducto producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    //metodo para devolver el precio total de una linea reserva
    public BigDecimal getTotal(){
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
