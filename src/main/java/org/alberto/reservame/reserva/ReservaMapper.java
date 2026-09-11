package org.alberto.reservame.reserva;


import org.alberto.reservame.cliente.ClienteMapper;
import org.alberto.reservame.producto.ProductoMapper;
import org.alberto.reservame.reserva.dtoReserva.ComprobarReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.DetalleReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.LineaReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.ReservaResponseDTO;
import org.alberto.reservame.usuario.UsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaMapper {

    private final ClienteMapper clienteMapper;
    private final ProductoMapper productoMapper;
    private final UsuarioMapper usuarioMapper;

    public ReservaMapper(ClienteMapper clienteMapper, ProductoMapper productoMapper, UsuarioMapper usuarioMapper) {
        this.clienteMapper = clienteMapper;
        this.productoMapper = productoMapper;
        this.usuarioMapper = usuarioMapper;
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

    public DetalleReservaResponseDTO toDetalleReservaResponseDTO(Reserva reserva){
        List<LineaReservaResponseDTO> listLineaReserva = reserva.getLineasReserva().stream()
                .map(this::toLineaReservaResponseDTO).toList();

        return new DetalleReservaResponseDTO(reserva.getId(),
                reserva.getIdPublico(),
                clienteMapper.toClienteDTO(reserva.getCliente()),
                reserva.getFechaRecogida(),
                reserva.getEstado().toString(),
                reserva.getNotas(),
                usuarioMapper.toEmpleadoDTO(reserva.getUsuario()),
                listLineaReserva);
    }
}
