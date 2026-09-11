package org.alberto.reservame.usuario;

import org.alberto.reservame.usuario.dtoUsuario.EmpleadoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public EmpleadoResponseDTO toEmpleadoDTO(Usuario usuario){

        return new EmpleadoResponseDTO(usuario.getId(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getRol().toString(),
                usuario.isActivo(),
                usuario.getFechaCreacion());
    }
}
