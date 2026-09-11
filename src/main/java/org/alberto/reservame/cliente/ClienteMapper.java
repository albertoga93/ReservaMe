package org.alberto.reservame.cliente;

import org.alberto.reservame.cliente.dtoCliente.ClienteResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public ClienteResponseDTO toClienteDTO (Cliente cliente){
        return new ClienteResponseDTO(cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }
}
