package org.alberto.reservame.security;


import jakarta.validation.Valid;
import org.alberto.reservame.security.dtoSecurity.LoginRequestDTO;
import org.alberto.reservame.security.dtoSecurity.LoginResponseDTO;
import org.alberto.reservame.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = jwtService.generarToken(usuario);

        LoginResponseDTO response = new LoginResponseDTO(
                token,
                usuario.getNombre(),
                usuario.getRol().name());

        return ResponseEntity.ok(response);
    }
}
