package com.example.demo.controller;

import com.example.demo.model.Ponto;
import com.example.demo.service.PontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/ponto")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PontoController {

    private final PontoService pontoService;

    @PostMapping("/marcar-ponto/{usuarioId}")
    public ResponseEntity marcarPonto(@PathVariable Long usuarioId) {

        if (!pontoService.checarUsuario(usuarioId)) {
            return ResponseEntity.ok().body("Usuário não encontrado");
        } else if (!pontoService.checarDiaDaSemanaValido(LocalDate.now())) {
            return ResponseEntity.ok().body("Sábados e domingos não são permiditos expedientes");
        }

        try {
            Ponto ponto = pontoService.registrarHora(usuarioId);
            return ResponseEntity.ok().body(ponto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
