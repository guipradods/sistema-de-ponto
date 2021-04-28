package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ponto {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate diaDoMes;
    private LocalTime pontoUm;
    private LocalTime pontoDois;
    private LocalTime pontoTres;
    private LocalTime pontoQuatro;
    private Double bancoDeHoras;

    public Ponto(LocalDate diaDoMes) {
        this.diaDoMes = diaDoMes;
    }

}
