package com.example.demo.controller;

import com.example.demo.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RequestMapping("/ponto/")
@RestController
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping()
    public List returnTime() {

//        List pontoBruto = pontoService.baterPonto();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        String formatDateTime = pontoBruto.format(formatter);

        return pontoService.baterPonto();
    }

}
