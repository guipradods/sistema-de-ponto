package com.example.demo.controller;

import com.example.demo.model.Ponto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PontoService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/ponto")
@RestController
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping("/marcar-ponto/{usuarioId}")
    public ResponseEntity marcarPonto(@PathVariable Usuario usuarioId) {

        if (!pontoService.checarUsuario(usuarioId)) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        } else if (!pontoService.checarDiaDaSemanaValido(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Sábados e domingos não são permiditos expedientes");
        }

        try {
            Ponto ponto = pontoService.registrarHora(usuarioId);
            if (pontoService.checarTodosOsPontos(ponto)) {
                return ResponseEntity.badRequest().body("Todos os pontos já foram marcados");
            }
            return ResponseEntity.ok().body(ponto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
