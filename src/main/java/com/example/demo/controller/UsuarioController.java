package com.example.demo.controller;


import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.UsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuario")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioController {

    private final UsuarioService usuarioService;
//    private final UsuarioUtil usuarioUtil;

    @PostMapping(path = "/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        if (!usuarioService.checarCPFDisponivel(usuarioDTO)) {
            return ResponseEntity.badRequest().body("O usuário já existe");
        } else if (!UsuarioUtil.checarCPFvalido(usuarioDTO)) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }
        try {
            usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.ok().body("Usuario cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possível realizar o cadastro");
        }

    }

}
