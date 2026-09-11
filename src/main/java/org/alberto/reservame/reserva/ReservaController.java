package org.alberto.reservame.reserva;

import org.springframework.web.bind.annotation.RestController;

@RestController("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
}
