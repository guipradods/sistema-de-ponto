package com.example.demo.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ponto {

    private Long id;
    private LocalDate diaDoMes;
    private LocalTime pontoUm;
    private LocalTime pontoDois;
    private LocalTime pontoTres;
    private LocalTime pontoQuatro;

    public LocalDate getDiaDoMes() {
        return diaDoMes;
    }

    public void setDiaDoMes(LocalDate diaDoMes) {
        this.diaDoMes = diaDoMes;
    }

    public LocalTime getPontoUm() {
        return pontoUm;
    }

    public void setPontoUm(LocalTime pontoUm) {
        this.pontoUm = pontoUm;
    }

    public LocalTime getPontoDois() {
        return pontoDois;
    }

    public void setPontoDois(LocalTime pontoDois) {
        this.pontoDois = pontoDois;
    }

    public LocalTime getPontoTres() {
        return pontoTres;
    }

    public void setPontoTres(LocalTime pontoTres) {
        this.pontoTres = pontoTres;
    }

    public LocalTime getPontoQuatro() {
        return pontoQuatro;
    }

    public void setPontoQuatro(LocalTime pontoQuatro) {
        this.pontoQuatro = pontoQuatro;
    }
}

