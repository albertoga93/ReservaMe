package org.alberto.reservame.reserva;

import jakarta.validation.Valid;
import org.alberto.reservame.reserva.dtoReserva.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController()
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PreAuthorize("hasAnyRole('DUENO','EMPLEADO')")
    @PostMapping()
    public ResponseEntity<DetalleReservaResponseDTO> crearReserva(@Valid @RequestBody CrearReservaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(dto));
    }

    @GetMapping("/publico/{codigoPublico}")
    public ResponseEntity<ComprobarReservaResponseDTO> comprobarReservaPublico(@PathVariable String codigoPublico ){
        return ResponseEntity.ok().body(reservaService.comprobarReservaPublico(codigoPublico));
    }

    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleReservaResponseDTO> detalleReserva(@PathVariable Long id){
        return ResponseEntity.ok().body(reservaService.detalleReserva(id));
    }

    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarReserva(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrega,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(required = false) Estado estado){

        return ResponseEntity.ok(reservaService.listarReservas(fechaEntrega, desde, hasta, estado));
    }

    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ReservaResponseDTO> cambiarEstadoLista(@PathVariable Long id, @RequestBody EditarEstadoReservaRequestDTO dto){
        return ResponseEntity.ok().body(reservaService.cambiarEstadoLista(id, dto));
    }


    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelarReserva(@PathVariable Long id){
        return ResponseEntity.ok().body(reservaService.cancelarReserva(id));
    }

    @PreAuthorize("hasAnyRole('DUENO', 'EMPLEADO')")
    @PatchMapping("/recoger")
    public ResponseEntity<ReservaResponseDTO> recogerReserva(@RequestBody  RecogerReservaRequestDTO dto){
        return ResponseEntity.ok().body(reservaService.recogerReserva(dto));
    }

}
