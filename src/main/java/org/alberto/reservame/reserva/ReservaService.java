package org.alberto.reservame.reserva;


import jakarta.transaction.Transactional;
import org.alberto.reservame.cliente.Cliente;
import org.alberto.reservame.cliente.ClienteRepository;
import org.alberto.reservame.exception.OperacionNoPermitidaException;
import org.alberto.reservame.exception.RecursoNoEncontradoException;
import org.alberto.reservame.producto.VarianteProducto;
import org.alberto.reservame.producto.VarianteRepository;
import org.alberto.reservame.reserva.dtoReserva.*;
import org.alberto.reservame.usuario.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ReservaResponseDTO> listarReservas(LocalDate fecharecogida, LocalDate desde, LocalDate hasta, Estado estado){

        if(fecharecogida != null && (desde != null || hasta != null)){
            throw new IllegalArgumentException("No se puede combinar ambos filro de fechas");
        }

        List<Reserva> reservaList = reservaRepository.findAll();

        return reservaList.stream()
                .filter(r -> fecharecogida == null || r.getFechaRecogida().toLocalDate().equals(fecharecogida))
                .filter(r -> desde == null || !r.getFechaRecogida().toLocalDate().isBefore(desde))
                .filter(r -> hasta == null || !r.getFechaRecogida().toLocalDate().isAfter(hasta))
                .filter(r -> estado != null || (r.getEstado() != Estado.CANCELADA && r.getEstado() != Estado.ENTREGADA))
                .filter(r -> estado == null || r.getEstado().equals(estado))
                .sorted(Comparator.comparing(Reserva::getFechaRecogida))
                .map(reservaMapper::toReservaResponseDTO)
                .toList();

    }


    public ReservaResponseDTO cambiarEstadoLista(Long id, EditarEstadoReservaRequestDTO dto){

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La reserva no existe"));

        if (reserva.getEstado() == Estado.PENDIENTE && dto.getEstado() == Estado.LISTA) {
            reserva.setEstado(dto.getEstado());
        } else {
            throw new OperacionNoPermitidaException("Transición de estado no permitida");
        }

        Reserva reservaActualizada = reservaRepository.save(reserva);
        return reservaMapper.toReservaResponseDTO(reservaActualizada);


    }

    @Transactional
    public ReservaResponseDTO cancelarReserva(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La reserva no existe"));

        if (reserva.getEstado() == Estado.ENTREGADA) {
            throw new OperacionNoPermitidaException("No se puede cancelar una reserva ya entregada");
        }

        if (reserva.getEstado() == Estado.CANCELADA) {
            throw new OperacionNoPermitidaException("La reserva ya está cancelada");
        }

        List<LineaReserva> listLineas = reserva.getLineasReserva();

        for(LineaReserva linea : listLineas){
            VarianteProducto variante = linea.getProducto();
            variante.setStock(linea.getCantidad() + variante.getStock());

        }

        reserva.setEstado(Estado.CANCELADA);

        Reserva reservaActualizada = reservaRepository.save(reserva);

        return reservaMapper.toReservaResponseDTO(reservaActualizada);
    }

    public ReservaResponseDTO recogerReserva(RecogerReservaRequestDTO dto){
        Reserva reserva = reservaRepository.findByIdPublico(dto.getCodigoPublico())
                .orElseThrow(()-> new RecursoNoEncontradoException("Reserva no encontrada"));

        if (reserva.getEstado() == Estado.ENTREGADA) {
            throw new OperacionNoPermitidaException("La reserva ya ha sido retirada");
        }

        if (reserva.getEstado() == Estado.CANCELADA) {
            throw new OperacionNoPermitidaException("No se puede recoger una reserva cancelada");
        }

        if(reserva.getEstado() == Estado.PENDIENTE){
            throw new OperacionNoPermitidaException("La reserva aun no esta disponible para recogida");
        }

        reserva.setEstado(Estado.ENTREGADA);

        Reserva reservaActualizada = reservaRepository.save(reserva);
        return reservaMapper.toReservaResponseDTO(reservaActualizada);

    }




    private String generarCodigoPublico() {
        return UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    private Usuario obtenerUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
