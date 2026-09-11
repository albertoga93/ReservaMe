package org.alberto.reservame.reserva;

import jakarta.validation.Valid;
import org.alberto.reservame.reserva.dtoReserva.CrearReservaRequestDTO;
import org.alberto.reservame.reserva.dtoReserva.DetalleReservaResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
