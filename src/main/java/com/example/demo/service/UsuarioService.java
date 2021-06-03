package com.example.demo.service;

import com.example.demo.builder.UsuarioBuilder;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.PontoRepository;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioService {

    private final UsuarioBuilder usuarioBuilder;
    private final UsuarioRepository usuarioRepository;
    private final PontoRepository pontoRepository;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        var novoUsuario = usuarioBuilder.usuarioDtoToEntity(usuarioDTO);
        usuarioRepository.save(novoUsuario);
    }

    public Boolean checarCPFDisponivel(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findByCpf(usuarioDTO.getCpf()) == null;
    }

    public void atualizarBancoDeHoras(Usuario usuario) {
        var horasTrabalhadas = pontoRepository.findSumHorasTrabalhadas(usuario);
        usuario.setBancoDeHoras(horasTrabalhadas);
    }

}
