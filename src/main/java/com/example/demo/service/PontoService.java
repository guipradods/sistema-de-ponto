package com.example.demo.service;

import com.example.demo.model.Ponto;
import com.example.demo.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    public Ponto registrarHora() {

        if (!checarDataNoBanco(LocalDate.now())) {
            pontoRepository.save(new Ponto(LocalDate.now()));
        }

        Ponto ponto = pontoRepository.findByDiaDoMes(LocalDate.now());

        if (ponto.getPontoUm() == null) {
            ponto.setPontoUm(LocalTime.now());
            pontoRepository.save(ponto);
        } else if (ponto.getPontoDois() == null) {
            ponto.setPontoDois(LocalTime.now());
            pontoRepository.save(ponto);
        } else if (ponto.getPontoTres() == null) {
            if (ponto.getPontoDois().plusHours(1).isBefore(LocalTime.now())) {
                ponto.setPontoTres(LocalTime.now());
                pontoRepository.save(ponto);
            } else {
                return ponto;
            }
        } else if (ponto.getPontoQuatro() == null) {
            ponto.setPontoQuatro(LocalTime.now());
            pontoRepository.save(ponto);
        }

        return ponto;

    }

    public Boolean checarDataNoBanco(LocalDate data) {
        return pontoRepository.findByDiaDoMes(data) != null;
    }

    public Boolean checarDiaDaSemanaValido(LocalDate data) {
        if (data.getDayOfWeek() == DayOfWeek.of(6) || data.getDayOfWeek() == DayOfWeek.of(7)) {
            return false;
        }
        return true;
    }

}
