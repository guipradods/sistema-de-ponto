package com.example.demo.controller;


import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        if (usuarioService.checarCPFdisponivel(usuarioDTO)) {
            usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.ok().body("Usuario Cadastrado");
        }
        return ResponseEntity.badRequest().body("Não foi possível realizar o cadastro");

    }

}
