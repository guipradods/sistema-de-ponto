package com.example.demo.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDTO {

    private String nome;
    private String cpf;

}
