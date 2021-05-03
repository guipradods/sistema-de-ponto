package com.example.demo.repository;

import com.example.demo.model.Ponto;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    Ponto findByDiaDoMes(LocalDate diaDoMes);

    Ponto findByDiaDoMesAndUsuario(LocalDate diaDoMes, Usuario usuarioId);

    Boolean findByUsuario(Usuario usuarioId);
}
