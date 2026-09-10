package org.alberto.reservame.usuario;


import jakarta.validation.Valid;
import org.alberto.reservame.usuario.dtoUsuario.CrearEmpleadoRequestDTO;
import org.alberto.reservame.usuario.dtoUsuario.EmpleadoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('DUENO')")
    @PostMapping()
    public ResponseEntity<EmpleadoResponseDTO> crearEmpleado(@Valid @RequestBody CrearEmpleadoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearEmpleado(dto));
    }

    @PreAuthorize("hasRole('DUENO')")
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<EmpleadoResponseDTO> desactivarEmpleado(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.desactivarEmpleado(id));
    }

    @PreAuthorize("hasRole('DUENO')")
    @PatchMapping("/{id}/activar")
    public ResponseEntity<EmpleadoResponseDTO> activarEmpleado(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.activarEmpleado(id));
    }


}
