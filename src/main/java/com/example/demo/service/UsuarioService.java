package com.example.demo.service;

import com.example.demo.builder.UsuarioBuilder;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {

        var novoUsuario = usuarioBuilder.usuarioDtoToEntity(usuarioDTO);
        usuarioRepository.save(novoUsuario);

    }

    public Boolean checarCPFdisponivel(UsuarioDTO usuario) {
        return usuarioRepository.findByCpf(usuario.getCpf()) == null;
    }

}
