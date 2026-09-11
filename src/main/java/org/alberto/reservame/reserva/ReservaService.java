package org.alberto.reservame.reserva;


import jakarta.transaction.Transactional;
import org.alberto.reservame.cliente.Cliente;
import org.alberto.reservame.cliente.ClienteRepository;
import org.alberto.reservame.exception.OperacionNoPermitidaException;
import org.alberto.reservame.exception.RecursoNoEncontradoException;
import org.alberto.reservame.producto.VarianteProducto;
import org.alberto.reservame.producto.VarianteRepository;
import org.alberto.reservame.reserva.dtoReserva.ComprobarReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.CrearReservaRequestDTO;
import org.alberto.reservame.reserva.dtoReserva.DetalleReservaResponseDTO;
import org.alberto.reservame.reserva.dtoReserva.LineaReservaRequestDTO;
import org.alberto.reservame.usuario.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final ClienteRepository clienteRepository;
    private final VarianteRepository varianteRepository;

    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, ClienteRepository clienteRepository, VarianteRepository varianteRepository) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.clienteRepository = clienteRepository;
        this.varianteRepository = varianteRepository;

    }

    @Transactional
    public DetalleReservaResponseDTO crearReserva(CrearReservaRequestDTO dto) {

        Cliente cliente = clienteRepository.findByEmail(dto.getCliente().getEmail())
                .orElseGet(() -> clienteRepository.save(new Cliente(
                        dto.getCliente().getNombre(),
                        dto.getCliente().getEmail(),
                        dto.getCliente().getTelefono()
                )));

        Map<Long, VarianteProducto> variantesMap = new HashMap<>();

        for (LineaReservaRequestDTO linea : dto.getListLineaReserva()) {

            VarianteProducto variante = varianteRepository.findById(linea.getVarianteId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Variante no encontrada"));

            if (!variante.isActivo()) {
                throw new OperacionNoPermitidaException("No se puede reservar una variante inactiva");
            }

            if (linea.getCantidad() > variante.getStock()) {
                throw new OperacionNoPermitidaException("Stock insuficiente para " + variante.getName());
            }

            variantesMap.put(linea.getVarianteId(), variante);
        }

        Usuario usuarioActual = obtenerUsuarioAutenticado();

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setUsuario(usuarioActual);
        reserva.setFechaRecogida(dto.getFechaRecogida());
        reserva.setNotas(dto.getNotas());
        reserva.setEstado(Estado.PENDIENTE);
        reserva.setIdPublico(generarCodigoPublico());

        for (LineaReservaRequestDTO lineaDTO : dto.getListLineaReserva()) {

            VarianteProducto variante = variantesMap.get(lineaDTO.getVarianteId());

            variante.setStock(variante.getStock() - lineaDTO.getCantidad());

            LineaReserva lineaReserva = new LineaReserva(
                    lineaDTO.getCantidad(),
                    variante.getPrecio(),
                    reserva,
                    variante
            );

            reserva.getLineasReserva().add(lineaReserva);
        }

        Reserva reservaGuardada = reservaRepository.save(reserva);

        return reservaMapper.toDetalleReservaResponseDTO(reservaGuardada);
    }


    public ComprobarReservaResponseDTO comprobarReservaPublico(String codigoPublico){
        if(codigoPublico.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        Reserva reserva = reservaRepository.findByIdPublico(codigoPublico.toUpperCase()).orElseThrow(() -> new RecursoNoEncontradoException("La reserva no existe"));

        return reservaMapper.toComprobarReservaResponseDTO(reserva);
    }


    public DetalleReservaResponseDTO detalleReserva(Long id){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada"));
        return reservaMapper.toDetalleReservaResponseDTO(reserva);
    }







    private String generarCodigoPublico() {
        return UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    private Usuario obtenerUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
