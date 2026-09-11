package org.alberto.reservame.reserva;


import org.alberto.reservame.cliente.ClienteMapper;
import org.alberto.reservame.producto.ProductoMapper;
import org.alberto.reservame.reserva.dtoReserva.ComprobarReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.LineaReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.ReservaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    private final ClienteMapper clienteMapper;
    private final ProductoMapper productoMapper;

    public ReservaMapper(ClienteMapper clienteMapper, ProductoMapper productoMapper) {
        this.clienteMapper = clienteMapper;
        this.productoMapper = productoMapper;
    }

    public ComprobarReservaResponseDTO toComprobarReservaResponseDTO(Reserva reserva){
        return new ComprobarReservaResponseDTO(reserva.getEstado(), reserva.getFechaRecogida());
    }

    public ReservaResponseDTO toReservaResponseDTO(Reserva reserva){
        return new ReservaResponseDTO(reserva.getId(),
                clienteMapper.toClienteDTO(reserva.getCliente()),
                reserva.getFechaRecogida(),
                reserva.getEstado(),
                reserva.getLineasReserva().size()
        );
    }

    public LineaReservaResponseDTO toLineaReservaResponseDTO(LineaReserva lineaReserva){
        return new LineaReservaResponseDTO(lineaReserva.getCantidad(),
                lineaReserva.getPrecioUnitario(),
                productoMapper.toVarianteDto(lineaReserva.getProducto()));
    }
}
