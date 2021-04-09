package com.example.demo.service;

import com.example.demo.model.Ponto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PontoService {

    Ponto ponto = new Ponto();

    public List baterPonto() {

        ponto.setDiaDoMes(LocalDate.now());

        if (ponto.getPontoUm() == null) {
            ponto.setPontoUm(LocalTime.now());
        } else if (ponto.getPontoDois() == null) {
            ponto.setPontoDois(LocalTime.now());
        } else if (ponto.getPontoTres() == null) {
            ponto.setPontoTres(LocalTime.now());
        } else if (ponto.getPontoQuatro() == null) {
            ponto.setPontoQuatro(LocalTime.now());
        }

        List lista = new ArrayList<>();
        lista.add(ponto.getDiaDoMes());
        lista.add(ponto.getPontoUm() == null ? ponto.getPontoUm() : ponto.getPontoUm().format(DateTimeFormatter.ofPattern("HH:mm")));
        lista.add(ponto.getPontoDois() == null ? ponto.getPontoDois() : ponto.getPontoDois().format(DateTimeFormatter.ofPattern("HH:mm")));
        lista.add(ponto.getPontoTres() == null ? ponto.getPontoTres() : ponto.getPontoTres().format(DateTimeFormatter.ofPattern("HH:mm")));
        lista.add(ponto.getPontoQuatro() == null ? ponto.getPontoQuatro() : ponto.getPontoQuatro().format(DateTimeFormatter.ofPattern("HH:mm")));

        return lista;

    }
//.format(DateTimeFormatter.ofPattern("HH:mm"))


}
