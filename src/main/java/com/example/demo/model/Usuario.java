package com.example.demo.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Double bancoDeHoras;

}
