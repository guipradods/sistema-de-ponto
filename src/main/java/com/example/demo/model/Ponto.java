package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ponto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDate diaDoMes;

    private LocalTime pontoUm;

    private LocalTime pontoDois;

    private LocalTime pontoTres;

    private LocalTime pontoQuatro;

    private Double horasTrabalhadas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Ponto(LocalDate diaDoMes) {
        this.diaDoMes = diaDoMes;
    }

    public Ponto(LocalDate diaDoMes, Usuario usuarioId) {
        this.diaDoMes = diaDoMes;
        this.usuario = usuarioId;
    }

}
