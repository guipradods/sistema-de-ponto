package com.example.demo.repository;

import com.example.demo.model.Ponto;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    @Query("SELECT p from Ponto p where p.diaDoMes = :diaDoMes and p.usuario = :usuarioId")
    Optional<Ponto> findByDiaDoMesAndUsuario(@Param("diaDoMes") LocalDate diaDoMes, @Param("usuarioId") Usuario usuarioId);

    @Query("SELECT sum(p.horasTrabalhadas) - (count(p.horasTrabalhadas) * 8) FROM Ponto p where p.usuario = :usuarioId")
    Double findSumHorasTrabalhadas(@Param("usuarioId") Usuario usuarioId);

}
