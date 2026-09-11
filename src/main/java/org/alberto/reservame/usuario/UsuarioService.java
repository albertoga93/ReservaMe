package org.alberto.reservame.usuario;

import org.alberto.reservame.exception.PasswordException;
import org.alberto.reservame.exception.RecursoNoEncontradoException;
import org.alberto.reservame.exception.UsuarioExistenteException;
import org.alberto.reservame.usuario.dtoUsuario.CrearEmpleadoRequestDTO;
import org.alberto.reservame.usuario.dtoUsuario.EmpleadoResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public EmpleadoResponseDTO crearEmpleado(CrearEmpleadoRequestDTO dto) {
        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new UsuarioExistenteException("El email ya se encuentra registrado");
        }

       if(!dto.getPassword().equals(dto.getConfirmarPassword())){
            throw new PasswordException("Las contraseñas no coinciden");
        }

        String passwordCrypt = passwordEncoder.encode(dto.getPassword());

        Usuario newUsuario = new Usuario(dto.getNombre(),dto.getEmail(), passwordCrypt, Cargo.EMPLEADO, true);

        Usuario usuarioGuardado = usuarioRepository.save(newUsuario);

        return usuarioMapper.toEmpleadoDTO(usuarioGuardado);

    }

    public EmpleadoResponseDTO desactivarEmpleado (Long id){
        Usuario user = usuarioRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("El usuario no existe"));

        if(user.getRol().compareTo(Cargo.DUENO) == 0){
            throw new IllegalArgumentException("No se puede desactivar un dueño");
        }

        if(!user.isActivo()){
            throw new IllegalArgumentException("El cliente ya se encuentra desactivado");
        }

        user.setActivo(false);

        Usuario usuarioGuardado = usuarioRepository.save(user);
        return usuarioMapper.toEmpleadoDTO(usuarioGuardado);
    }


    public EmpleadoResponseDTO activarEmpleado (Long id){
        Usuario user = usuarioRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("El usuario no existe"));



        if(user.isActivo()){
            throw new IllegalArgumentException("El cliente ya se encuentra activado");
        }

        user.setActivo(true);

        Usuario usuarioGuardado = usuarioRepository.save(user);
        return usuarioMapper.toEmpleadoDTO(usuarioGuardado);
    }

    public List<EmpleadoResponseDTO> listarUsuarios(Boolean activo){
        List<Usuario> usuariosList = usuarioRepository.findAll();

        return usuariosList.stream()
                .filter(em -> activo == null || em.isActivo() == activo)
                .map(usuarioMapper::toEmpleadoDTO)
                .toList();
    }






}
