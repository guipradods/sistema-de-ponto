package com.example.demo.builder;

import com.example.demo.model.Usuario;
import com.example.demo.model.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBuilder {

    public Usuario usuarioDtoToEntity(UsuarioDTO usuarioDTO) {
        return Usuario.builder().cpf(usuarioDTO.getCpf()).nome(usuarioDTO.getNome()).bancoDeHoras(0.0).build();
    }

    public UsuarioDTO usuarioToDTO(Usuario usuario) {
        return UsuarioDTO.builder().cpf(usuario.getCpf()).nome(usuario.getNome()).build();
    }

}
