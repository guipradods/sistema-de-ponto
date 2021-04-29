package com.example.demo.controller;

import com.example.demo.model.Ponto;
import com.example.demo.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/ponto")
@RestController
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping()
    public ResponseEntity marcarPonto() {

        if (pontoService.checarDiaDaSemanaValido(LocalDate.now())) {
            Ponto ponto = pontoService.registrarHora();
            return ResponseEntity.ok().body(ponto);
        }
        return ResponseEntity.badRequest().body("Sábados e domingos não são permiditos expedientes");
    }

}
